package vn.edu.ptit.sqa.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import vn.edu.ptit.sqa.constant.ConfigStatus;
import vn.edu.ptit.sqa.constant.ConfigType;
import vn.edu.ptit.sqa.constant.Term;
import vn.edu.ptit.sqa.dto.config.*;
import vn.edu.ptit.sqa.entity.Option;
import vn.edu.ptit.sqa.entity.config.ConfigHistory;
import vn.edu.ptit.sqa.entity.config.ConfigHistory_;
import vn.edu.ptit.sqa.entity.config.LoanConfig;
import vn.edu.ptit.sqa.entity.config.SavingConfig;
import vn.edu.ptit.sqa.exception.ClientVisibleException;
import vn.edu.ptit.sqa.helper.AuthHelper;
import vn.edu.ptit.sqa.model.Pagination;
import vn.edu.ptit.sqa.model.ResultPage;
import vn.edu.ptit.sqa.repository.ConfigHistoryRepository;
import vn.edu.ptit.sqa.repository.LoanConfigRepository;
import vn.edu.ptit.sqa.repository.LoanPurposeRepository;
import vn.edu.ptit.sqa.repository.SavingConfigRepository;
import vn.edu.ptit.sqa.service.ConfigService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {
    private final LoanPurposeRepository loanPurposeRepository;
    private final LoanConfigRepository loanConfigRepository;
    private final ConfigHistoryRepository configHistoryRepository;
    private final SavingConfigRepository savingConfigRepository;
    private final AuthHelper authHelper;

    @Override
    @Transactional
    public void changeLoanConfig(@Valid LoanConfigReq loanConfigReq) {
        ConfigHistory configHistory = loanConfigReq.toHistoryEntity();
        configHistory.setCreatedBy(authHelper.getSignedUser());

        // instantiate list of config to persist
        List<LoanConfig> loanConfigs = loanConfigReq.toConfigList();

        // require loan config for all purposes
        if (!isMatchPurposeSet(loanConfigs)) {
            throw new ClientVisibleException("{loan.config.purpose.same_set}");
        }

        loanConfigRepository.saveAll(loanConfigs);
    }

    @Override
    @Transactional
    public void changeSavingConfig(@Valid SavingConfigReq savingConfigReq) {
        ConfigHistory configHistory = savingConfigReq.toHistoryEntity();
        configHistory.setCreatedBy(authHelper.getSignedUser());

        // instantiate list of config to persist
        List<SavingConfig> savingConfigs = savingConfigReq.toConfigList();

        // require saving config for all terms
        if (!isMatchTermSet(savingConfigs)) {
            throw new ClientVisibleException("{saving.config.term.same_set}");
        }

        savingConfigRepository.saveAll(savingConfigs);
    }

    @Override
    public void updateConfigReviewResult(Integer configId, ReviewConfigResultDTO reviewConfigResult) {
        ConfigHistory configHistory = configHistoryRepository.findById(configId)
            .orElseThrow(() -> new ClientVisibleException("{config.not_exist}"));

        if (!configHistory.isResolvable()) {
            throw new ClientVisibleException("{config.not_resolvable}");
        }

        configHistory.setStatus(reviewConfigResult.isApproved() ? ConfigStatus.APPROVED : ConfigStatus.REJECTED);
        configHistory.setNote(reviewConfigResult.getNote());

        configHistoryRepository.save(configHistory);
    }

    @Override
    public ResultPage<ConfigHistoryDTO> getAllPendingConfig(Pagination pagination) {
        Sort sort = Sort.by(Sort.Direction.DESC, ConfigHistory_.START_DATE);

        Page<ConfigHistory> configs = configHistoryRepository.findAllByStatusIn(
            List.of(ConfigStatus.PENDING, ConfigStatus.EXPIRED),
            PageRequest.of(pagination.getPage(), pagination.getSize()).withSort(sort)
        );

        return ResultPage.<ConfigHistoryDTO>builder()
            .totalPages(configs.getTotalPages())
            .items(configs.map(ConfigHistoryDTO::new).toList())
            .build();
    }

    @Override
    public ResultPage<ConfigHistoryDTO> getAllConfigHistory(Pagination pagination) {
        Sort sort = Sort.by(Sort.Direction.DESC, ConfigHistory_.UPDATED_AT);

        Page<ConfigHistory> configs = configHistoryRepository.findAllByStatusIn(
            List.of(ConfigStatus.APPROVED, ConfigStatus.REJECTED),
            PageRequest.of(pagination.getPage(), pagination.getSize()).withSort(sort)
        );

        return ResultPage.<ConfigHistoryDTO>builder()
            .totalPages(configs.getTotalPages())
            .items(configs.map(ConfigHistoryDTO::new).toList())
            .build();
    }

    @Override
    public List<SavingConfigDTO> getCurrentSavingConfigs() {
        ConfigHistory lastConfig = configHistoryRepository.findLatestApprovedConfig(ConfigType.SAVING)
            .orElseThrow(() -> new ClientVisibleException("{config.no_active_found}"));

        List<SavingConfig> savingConfigs = savingConfigRepository.findAllByConfigHistory(lastConfig);

        return savingConfigs.stream()
            .map(SavingConfigDTO::new)
            .sorted(Comparator.comparingInt(SavingConfigDTO::getTermInMonth))
            .toList();
    }

    private boolean isMatchPurposeSet(List<LoanConfig> loanConfigs) {
        List<Long> purposes = loanPurposeRepository.findAll()
            .stream()
            .map(Option::getId)
            .toList();
        Set<Long> configuredPurposes = loanConfigs
            .stream()
            .map(loanConfig -> loanConfig.getPurpose().getId())
            .collect(Collectors.toSet());

        return configuredPurposes.size() == purposes.size() && configuredPurposes.containsAll(purposes);
    }

    private boolean isMatchTermSet(List<SavingConfig> savingConfigs) {
        List<Integer> terms = Arrays.stream(Term.values())
            .map(Term::getTotalMonth)
            .toList();
        Set<Integer> configuredTerms = savingConfigs.stream()
            .map(SavingConfig::getTermInMonth)
            .collect(Collectors.toSet());

        return configuredTerms.size() == terms.size() && configuredTerms.containsAll(terms);
    }

    private boolean isConflictedWithOthers(ConfigHistory newConfigHistory) {
        ConfigType configType = newConfigHistory.getConfigType();
        LocalDate startDate = newConfigHistory.getStartDate();
        return configHistoryRepository.existsByConfigTypeAndStartDate(configType, startDate);
    }
}
