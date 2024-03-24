package vn.edu.ptit.sqa.service;

import vn.edu.ptit.sqa.model.GeneralReport;
import vn.edu.ptit.sqa.model.LoanReport;
import vn.edu.ptit.sqa.model.SavingReport;

import java.time.LocalDate;

public interface ReportService {
    GeneralReport generateGeneralReport(LocalDate from, LocalDate to);
    LoanReport generateLoanReport(LocalDate from, LocalDate to);
    SavingReport generateSavingReport(LocalDate from, LocalDate to);
}
