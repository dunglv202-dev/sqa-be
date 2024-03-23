package vn.edu.ptit.sqa.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/configs/interest_rates")
public class InterestRateConfigController {
    @PutMapping("/loans/secured")
    @PreAuthorize("hasRole('MANAGER')")
    public void changeSecuredLoanInterestRates() {

    }

    @PutMapping("/loans/unsecured")
    @PreAuthorize("hasRole('MANAGER')")
    public void changeUnsecuredLoanInterestRates() {

    }

    @PutMapping("/savings")
    @PreAuthorize("hasRole('MANAGER')")
    public void changeSavingInterestRates() {

    }
}
