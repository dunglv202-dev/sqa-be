package vn.edu.ptit.sqa.dto.config;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.constant.Term;
import vn.edu.ptit.sqa.entity.config.SavingConfig;
import vn.edu.ptit.sqa.validator.InterestRate;

import java.util.Objects;

@Getter
@Setter
public class NewSavingConfigDTO implements ConfigDTO {
    @NotNull(message = "{saving.config.term.required}")
    private Term term;

    @InterestRate
    private double yearlyInterestRate;

    @Override
    public SavingConfig toEntity() {
        SavingConfig savingConfig = new SavingConfig();
        savingConfig.setTermInMonth(term.getTotalMonth());
        savingConfig.setYearlyInterestRate(yearlyInterestRate);

        return savingConfig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewSavingConfigDTO that = (NewSavingConfigDTO) o;
        return term == that.term;
    }

    @Override
    public int hashCode() {
        return Objects.hash(term);
    }
}
