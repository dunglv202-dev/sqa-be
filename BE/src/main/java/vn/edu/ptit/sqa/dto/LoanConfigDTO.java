package vn.edu.ptit.sqa.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.entity.LoanConfig;
import vn.edu.ptit.sqa.entity.LoanPurpose;

import java.math.BigDecimal;
import java.util.Objects;

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
        LoanPurpose loanPurpose = new LoanPurpose();
        loanPurpose.setId(this.purposeId);

        LoanConfig loanConfig = new LoanConfig();
        loanConfig.setPurpose(loanPurpose);
        loanConfig.setYearlyInterestRate(this.yearlyInterestRate);
        loanConfig.setLimit(this.limit);

        return loanConfig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanConfigDTO that = (LoanConfigDTO) o;
        return Objects.equals(purposeId, that.purposeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purposeId);
    }
}
