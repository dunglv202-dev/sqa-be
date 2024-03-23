package vn.edu.ptit.sqa.dto.loan;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.dto.generic.ConfigDTO;
import vn.edu.ptit.sqa.entity.config.LoanConfig;
import vn.edu.ptit.sqa.entity.loan.LoanPurpose;
import vn.edu.ptit.sqa.validator.InterestRate;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
public class LoanConfigDTO implements ConfigDTO {
    @NotNull(message = "{loan.config.purpose.required}")
    private Long purposeId;

    @InterestRate
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
