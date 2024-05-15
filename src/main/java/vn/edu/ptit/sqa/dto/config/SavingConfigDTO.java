package vn.edu.ptit.sqa.dto.config;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.entity.config.SavingConfig;

@Getter
@Setter
public class SavingConfigDTO {
    private Integer termInMonth;
    private double yearlyInterestRate;

    public SavingConfigDTO(SavingConfig savingConfig) {
        this.termInMonth = savingConfig.getTermInMonth() == null ? 0 : savingConfig.getTermInMonth();
        this.yearlyInterestRate = savingConfig.getYearlyInterestRate();
    }
}
