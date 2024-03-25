package vn.edu.ptit.sqa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.ptit.sqa.constant.LoanType;
import vn.edu.ptit.sqa.dto.config.*;
import vn.edu.ptit.sqa.model.Pagination;
import vn.edu.ptit.sqa.model.ResultPage;
import vn.edu.ptit.sqa.service.ConfigService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/configs")
@RequiredArgsConstructor
public class ConfigController {
    private final ConfigService configService;

    @GetMapping("/loan")
    @PreAuthorize("hasAnyRole('MANAGER', 'DIRECTOR')")
    public List<LoanConfigDTO> getCurrentLoanConfigs(@RequestParam LoanType type) {
        return configService.getCurrentLoanConfigs(type);
    }

    @PostMapping("/loan")
    @PreAuthorize("hasRole('MANAGER')")
    public void changeLoanConfig(@RequestBody LoanConfigReq loanConfigReq) {
        configService.changeLoanConfig(loanConfigReq);
    }

    @GetMapping("/saving")
    @PreAuthorize("hasAnyRole('MANAGER', 'DIRECTOR')")
    public List<SavingConfigDTO> getCurrentSavingConfigs() {
        return configService.getCurrentSavingConfigs();
    }

    @PostMapping("/saving")
    @PreAuthorize("hasRole('MANAGER')")
    public void changeSavingConfig(@RequestBody SavingConfigReq savingConfigReq) {
        configService.changeSavingConfig(savingConfigReq);
    }

    @GetMapping("/resolved")
    @PreAuthorize("hasRole('DIRECTOR')")
    public ResultPage<ConfigHistoryDTO> getAllConfigHistory(Pagination pagination) {
        return configService.getAllConfigHistory(pagination);
    }

    @GetMapping("/unresolved")
    @PreAuthorize("hasRole('DIRECTOR')")
    public ResultPage<ConfigHistoryDTO> getPendingConfigRequests(Pagination pagination) {
        return configService.getAllPendingConfig(pagination);
    }

    @PutMapping("/{configId}/result")
    @PreAuthorize("hasRole('DIRECTOR')")
    public void updateReviewResultForConfigReq(@PathVariable Integer configId, @RequestBody ReviewConfigResultDTO reviewConfigResult) {
        configService.updateConfigReviewResult(configId, reviewConfigResult);
    }
}
