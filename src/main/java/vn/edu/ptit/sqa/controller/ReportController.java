package vn.edu.ptit.sqa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.sqa.model.Period;
import vn.edu.ptit.sqa.model.GeneralReport;
import vn.edu.ptit.sqa.model.LoanReport;
import vn.edu.ptit.sqa.service.ReportService;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    /* TODO: add validation for period */
    @GetMapping("/general")
    @PreAuthorize("hasRole('MANAGER')")
    public GeneralReport getGeneralReport(@Valid Period period) {
        return reportService.generateGeneralReport(period.getFrom(), period.getTo());
    }

    @GetMapping("/loan")
    @PreAuthorize("hasRole('MANAGER')")
    public LoanReport getLoanReport(@Valid Period period) {
        return reportService.generateLoanReport(period.getFrom(), period.getTo());
    }
}
