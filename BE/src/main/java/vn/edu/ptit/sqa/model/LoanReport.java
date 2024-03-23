package vn.edu.ptit.sqa.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class LoanReport {
    private long numberOfLoan;
    private BigDecimal amountForLending;
    private BigDecimal incomeFromLoanInterest;
    private List<LoanPurposeDistribution> purposeDistribution;
}
