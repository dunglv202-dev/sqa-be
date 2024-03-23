package vn.edu.ptit.sqa.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import vn.edu.ptit.sqa.dto.LoanConfigDTO;
import vn.edu.ptit.sqa.dto.LoanConfigReq;
import vn.edu.ptit.sqa.entity.LoanConfig;
import vn.edu.ptit.sqa.entity.LoanPurpose;
import vn.edu.ptit.sqa.entity.Option;
import vn.edu.ptit.sqa.exception.ClientVisibleException;
import vn.edu.ptit.sqa.repository.LoanConfigRepository;
import vn.edu.ptit.sqa.repository.LoanPurposeRepository;
import vn.edu.ptit.sqa.service.ConfigService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {
    private final LoanPurposeRepository loanPurposeRepository;
    private final LoanConfigRepository loanConfigRepository;

    @Override
    public void changeLoanConfig(@Valid LoanConfigReq loanConfigReq) {
        // check for conflict
        if (loanConfigRepository.existsByTypeAndStartDate(loanConfigReq.getType(), loanConfigReq.getStartDate())) {
            throw new ClientVisibleException("{loan.config.conflict}");
        }

        // require loan config for all purposes
        List<Long> purposes = loanPurposeRepository.findAll()
            .stream()
            .map(Option::getId)
            .toList();
        Set<Long> purposesIncludedInConfigReq = loanConfigReq.getConfigs()
            .stream()
            .map(LoanConfigDTO::getPurposeId)
            .collect(Collectors.toSet());
        if (purposes.size() != purposesIncludedInConfigReq.size()
            || !purposesIncludedInConfigReq.containsAll(purposes)) {
            throw new ClientVisibleException("{loan.config.purpose.same_set}");
        }

        // instantiate list of config to persist
        List<LoanConfig> loanConfigs = new ArrayList<>();
        loanConfigReq.getConfigs().forEach((config) -> {
            // build model
            LoanConfig loanConfig = config.toEntity();
            loanConfig.setType(loanConfigReq.getType());
            loanConfig.setStartDate(loanConfigReq.getStartDate());

            // add to persistent list
            loanConfigs.add(loanConfig);
        });

        loanConfigRepository.saveAll(loanConfigs);
    }
}
