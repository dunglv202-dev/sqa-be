package vn.edu.ptit.sqa.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import vn.edu.ptit.sqa.dto.LoanConfigReq;
import vn.edu.ptit.sqa.entity.LoanConfig;
import vn.edu.ptit.sqa.entity.LoanPurpose;
import vn.edu.ptit.sqa.exception.ClientVisibleException;
import vn.edu.ptit.sqa.repository.LoanConfigRepository;
import vn.edu.ptit.sqa.repository.LoanPurposeRepository;
import vn.edu.ptit.sqa.service.ConfigService;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {
    private final LoanPurposeRepository loanPurposeRepository;
    private final LoanConfigRepository loanConfigRepository;

    @Override
    public void changeLoanConfig(@Valid LoanConfigReq loanConfigReq) {
        List<LoanConfig> loanConfigs = new ArrayList<>();

        // check for conflict
        if (loanConfigRepository.existsByTypeAndStartDate(loanConfigReq.getType(), loanConfigReq.getStartDate())) {
            throw new ClientVisibleException("{loan.config.conflict}");
        }

        loanConfigReq.getConfigs().forEach((config) -> {
            LoanPurpose purpose = loanPurposeRepository.findById(config.getPurposeId())
                .orElseThrow(() -> new ClientVisibleException(
                    "{loan.config.purpose.not_exist} - ID: " + config.getPurposeId()
                ));

            // build model
            LoanConfig loanConfig = config.toEntity();
            loanConfig.setType(loanConfigReq.getType());
            loanConfig.setPurpose(purpose);
            loanConfig.setStartDate(loanConfigReq.getStartDate());

            // add to persistent list
            loanConfigs.add(loanConfig);
        });

        loanConfigRepository.saveAll(loanConfigs);
    }
}
