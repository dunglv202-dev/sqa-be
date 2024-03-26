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
    private Long numberOfLoan;
    private BigDecimal amountForLending;
    private BigDecimal incomeFromLoanInterest;
    private Integer numberOfNewCustomer;
    private BigDecimal interestAmount;
    private Long uncollectedDueLoan;
    private BigDecimal uncollectedAmount;
    private List<LoanPurposeDistribution> purposeDistribution;
    private List<LoanTypeDistribution> typeDistributions;
}
