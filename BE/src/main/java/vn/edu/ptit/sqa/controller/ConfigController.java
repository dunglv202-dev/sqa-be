package vn.edu.ptit.sqa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.sqa.dto.loan.LoanConfigReq;
import vn.edu.ptit.sqa.dto.saving.SavingConfigReq;
import vn.edu.ptit.sqa.service.ConfigService;

@RestController
@RequestMapping("/api/v1/configs")
@RequiredArgsConstructor
public class ConfigController {
    private final ConfigService configService;

    @PostMapping("/loan")
    @PreAuthorize("hasRole('MANAGER')")
    public void changeLoanConfig(@RequestBody LoanConfigReq loanConfigReq) {
        configService.changeLoanConfig(loanConfigReq);
    }

    @PostMapping("/saving")
    @PreAuthorize("hasRole('MANAGER')")
    public void changeSavingConfig(@RequestBody SavingConfigReq savingConfigReq) {
        configService.changeSavingConfig(savingConfigReq);
    }
}
