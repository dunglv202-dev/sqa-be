package vn.edu.ptit.sqa.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import vn.edu.ptit.sqa.constant.ConfigStatus;
import vn.edu.ptit.sqa.constant.ConfigType;
import vn.edu.ptit.sqa.constant.Term;
import vn.edu.ptit.sqa.dto.config.ConfigHistoryDTO;
import vn.edu.ptit.sqa.dto.config.LoanConfigReq;
import vn.edu.ptit.sqa.dto.config.ReviewConfigResultDTO;
import vn.edu.ptit.sqa.dto.config.SavingConfigReq;
import vn.edu.ptit.sqa.entity.config.ConfigHistory;
import vn.edu.ptit.sqa.entity.config.LoanConfig;
import vn.edu.ptit.sqa.entity.Option;
import vn.edu.ptit.sqa.entity.config.SavingConfig;
import vn.edu.ptit.sqa.exception.ClientVisibleException;
import vn.edu.ptit.sqa.helper.AuthHelper;
import vn.edu.ptit.sqa.repository.ConfigHistoryRepository;
import vn.edu.ptit.sqa.repository.LoanConfigRepository;
import vn.edu.ptit.sqa.repository.LoanPurposeRepository;
import vn.edu.ptit.sqa.service.ConfigService;

import java.time.LocalDate;
import java.util.Arrays;
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
    private final AuthHelper authHelper;

    @Override
    @Transactional
    public void changeLoanConfig(@Valid LoanConfigReq loanConfigReq) {
        ConfigHistory configHistory = loanConfigReq.toHistoryEntity();
        configHistory.setCreatedBy(authHelper.getSignedUser());

        // check for conflict
        if (isConflictedWithOthers(configHistory)) {
            throw new ClientVisibleException("{config.conflict}");
        }

        // instantiate list of config to persist
        List<LoanConfig> loanConfigs = loanConfigReq.toConfigList();

        // require loan config for all purposes
        if (!isMatchPurposeSet(loanConfigs)) {
            throw new ClientVisibleException("{loan.config.purpose.same_set}");
        }

        configHistoryRepository.save(configHistory);
        loanConfigRepository.saveAll(loanConfigs);
    }

    @Override
    @Transactional
    public void changeSavingConfig(@Valid SavingConfigReq savingConfigReq) {
        ConfigHistory configHistory = savingConfigReq.toHistoryEntity();
        configHistory.setCreatedBy(authHelper.getSignedUser());

        // check for conflict
        if (isConflictedWithOthers(configHistory)) {
            throw new ClientVisibleException("{config.conflict}");
        }

        // instantiate list of config to persist
        List<SavingConfig> savingConfigs = savingConfigReq.toConfigList();

        // require saving config for all terms
        if (!isMatchTermSet(savingConfigs)) {
            throw new ClientVisibleException("{saving.config.term.same_set}");
        }

        configHistoryRepository.save(configHistory);
    }

    @Override
    public void updateConfigReviewResult(Integer configId, ReviewConfigResultDTO reviewConfigResult) {
        ConfigHistory configHistory = configHistoryRepository.findById(configId)
            .orElseThrow(() -> new ClientVisibleException("{config.not_exist}"));

        configHistory.setStatus(reviewConfigResult.isApproved() ? ConfigStatus.APPROVED : ConfigStatus.REJECTED);
        configHistory.setNote(reviewConfigResult.getNote());

        configHistoryRepository.save(configHistory);
    }

    @Override
    public List<ConfigHistoryDTO> getAllPendingConfig() {
        return configHistoryRepository.getAllActivePendingConfigs()
            .stream()
            .map(ConfigHistoryDTO::new)
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
