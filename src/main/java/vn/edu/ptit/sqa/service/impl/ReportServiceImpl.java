package vn.edu.ptit.sqa.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.ptit.sqa.model.*;
import vn.edu.ptit.sqa.repository.LoanRepository;
import vn.edu.ptit.sqa.repository.SavingRepository;
import vn.edu.ptit.sqa.service.ReportService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
            .lendingAmount(Objects.requireNonNullElse(loanSummary.getTotalAmount(), BigDecimal.ZERO))
            .numberOfSaving(savingSummary.getNewSaving())
            .savingDepositAmount(Objects.requireNonNullElse(savingSummary.getTotalDepositAmount(), BigDecimal.ZERO))
            .build();
    }

    public LoanReport generateLoanReport(LocalDate from, LocalDate to) {
        NewLoanSummary loanSummary = loanRepository.summaryForNewLoan(from, to);
        UncollectedLoanSummary uncollectedLoanSummary = loanRepository.summaryForUncollectedLoan(to);
        int newCustomer = loanRepository.countNewCustomer(from, to);
        List<LoanPurposeDistribution> loanPurposeDistributions = loanRepository.getLoanDistributionByPurpose(from, to);
        List<LoanTypeDistribution> loanTypeDistributions = loanRepository.getLoanDistributionByType(from, to);

        return LoanReport.builder()
            .numberOfLoan(loanSummary.getNewLoan())
            .amountForLending(Objects.requireNonNullElse(loanSummary.getTotalAmount(), BigDecimal.ZERO))
            .numberOfNewCustomer(newCustomer)
            .purposeDistribution(loanPurposeDistributions)
            .uncollectedDueLoan(uncollectedLoanSummary.getUncollectedDueLoan())
            .uncollectedAmount(uncollectedLoanSummary.getUncollectedAmount())
            .typeDistributions(loanTypeDistributions)
            .build();
    }

    @Override
    public SavingReport generateSavingReport(LocalDate from, LocalDate to) {
        NewSavingSummary savingSummary = savingRepository.summaryForNewSaving(from, to);

        return SavingReport.builder()
            .numberOfSavingAccount(savingSummary.getNewSaving())
            .depositAmount(Objects.requireNonNullElse(savingSummary.getTotalDepositAmount(), BigDecimal.ZERO))
            .build();
    }
}
