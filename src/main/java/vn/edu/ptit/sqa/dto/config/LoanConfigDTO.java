package vn.edu.ptit.sqa.dto.config;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.dto.loan.LoanPurposeDTO;
import vn.edu.ptit.sqa.entity.config.LoanConfig;

import java.math.BigDecimal;

@Getter
@Setter
public class LoanConfigDTO {
    private LoanPurposeDTO purpose;
    private double yearlyInterestRate;
    private BigDecimal limit;

    public LoanConfigDTO(LoanConfig loanConfig) {
        this.purpose = new LoanPurposeDTO(loanConfig.getPurpose());
        this.yearlyInterestRate = loanConfig.getYearlyInterestRate();
        this.limit = loanConfig.getLimit();
    }
}
