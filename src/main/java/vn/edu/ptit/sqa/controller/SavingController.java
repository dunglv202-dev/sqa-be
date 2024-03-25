package vn.edu.ptit.sqa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.sqa.dto.saving.CustomerSavingListing;
import vn.edu.ptit.sqa.dto.saving.SavingDTO;
import vn.edu.ptit.sqa.model.Pagination;
import vn.edu.ptit.sqa.model.ResultPage;
import vn.edu.ptit.sqa.model.spec.SavingSpec;
import vn.edu.ptit.sqa.service.SavingService;

@RestController
@RequestMapping("/api/v1/savings")
@RequiredArgsConstructor
public class SavingController {
    private final SavingService savingService;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('MANAGER', 'EMPLOYEE')")
    public ResultPage<SavingDTO> getAllSavings(SavingSpec spec, Pagination pagination) {
        return savingService.getAllSavings(spec, pagination);
    }

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'EMPLOYEE')")
    public CustomerSavingListing getCustomerSavingAccounts(@PathVariable Long id) {
        return savingService.getCustomerSavingAccounts(id);
    }
}
