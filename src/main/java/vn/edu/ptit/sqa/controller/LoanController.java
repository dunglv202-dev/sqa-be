package vn.edu.ptit.sqa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.sqa.dto.loan.CustomerLoanListing;
import vn.edu.ptit.sqa.dto.loan.DetailLoan;
import vn.edu.ptit.sqa.dto.loan.LoanDTO;
import vn.edu.ptit.sqa.model.Pagination;
import vn.edu.ptit.sqa.model.ResultPage;
import vn.edu.ptit.sqa.model.spec.LoanSpec;
import vn.edu.ptit.sqa.service.LoanService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('MANAGER', 'EMPLOYEE', 'DIRECTOR')")
    public ResultPage<LoanDTO> getAllLoans(LoanSpec spec, Pagination pagination) {
        return loanService.getAllLoans(spec, pagination);
    }

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'EMPLOYEE', 'DIRECTOR')")
    public CustomerLoanListing getCustomerLoans(@PathVariable Long id) {
        return loanService.getCustomerLoans(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'EMPLOYEE', 'DIRECTOR')")
    public DetailLoan getLoanDetail(@PathVariable Long id) {
        return loanService.getDetailLoan(id);
    }
}
