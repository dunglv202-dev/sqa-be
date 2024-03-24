package vn.edu.ptit.sqa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.entity.loan.LoanPurpose;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class LoanPurposeDistribution {
    private String loanPurpose;
    private BigDecimal totalAmount;

    public LoanPurposeDistribution(LoanPurpose loanPurpose, BigDecimal totalAmount) {
        this.loanPurpose = loanPurpose.getLabel();
        this.totalAmount = totalAmount;
    }
}
