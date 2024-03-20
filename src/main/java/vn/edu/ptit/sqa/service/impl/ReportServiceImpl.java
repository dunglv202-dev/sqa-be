package vn.edu.ptit.sqa.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.ptit.sqa.model.GeneralReport;
import vn.edu.ptit.sqa.model.NewLoanSummary;
import vn.edu.ptit.sqa.model.NewSavingSummary;
import vn.edu.ptit.sqa.repository.LoanRepository;
import vn.edu.ptit.sqa.repository.SavingRepository;
import vn.edu.ptit.sqa.service.ReportService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final LoanRepository loanRepository;
    private final SavingRepository savingRepository;

    @Override
    public GeneralReport generateGeneralReport(LocalDate from, LocalDate to) {
        NewLoanSummary loanSummary = loanRepository.summaryForNewLoan(from, to);
        NewSavingSummary savingSummary = savingRepository.summaryForNewSaving(from, to);

        return GeneralReport.builder()
            .numberOfLoan(loanSummary.getNewLoan())
            .lendingAmount(loanSummary.getTotalAmount())
            .numberOfSaving(savingSummary.getNewSaving())
            .savingDepositAmount(savingSummary.getTotalAmount())
            .build();
    }
}
