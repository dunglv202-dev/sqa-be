package vn.edu.ptit.sqa.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.entity.LoanConfig;

import java.math.BigDecimal;

@Getter
@Setter
public class LoanConfigDTO {
    @NotNull(message = "{loan.config.purpose.required}")
    private Long purposeId;

    @DecimalMax(value = "1", message = "{loan.config.interest_rate.invalid}")
    @Positive(message = "{loan.config.interest_rate.invalid}")
    private double yearlyInterestRate;

    @Positive(message = "{loan.config.limit.positive}")
    private BigDecimal limit;

    public LoanConfig toEntity() {
        LoanConfig loanConfig = new LoanConfig();
        loanConfig.setYearlyInterestRate(this.yearlyInterestRate);
        loanConfig.setLimit(this.limit);

        return loanConfig;
    }
}
