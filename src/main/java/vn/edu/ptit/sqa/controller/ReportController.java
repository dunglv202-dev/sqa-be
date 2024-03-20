package vn.edu.ptit.sqa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.sqa.dto.ReportPeriod;
import vn.edu.ptit.sqa.model.GeneralReport;
import vn.edu.ptit.sqa.service.ReportService;

import java.time.LocalDate;
import java.time.YearMonth;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    /* TODO: add validation for period */
    @GetMapping("/general")
    public GeneralReport getGeneralReport(ReportPeriod reportPeriod) {
        LocalDate from = LocalDate.of(reportPeriod.getYear(), reportPeriod.getMonth(), 1);
        LocalDate to = YearMonth.from(from).atEndOfMonth();

        return reportService.generateGeneralReport(from, to);
    }
}
