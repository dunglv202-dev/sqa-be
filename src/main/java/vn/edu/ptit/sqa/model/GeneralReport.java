package vn.edu.ptit.sqa.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class GeneralReport {
    private long numberOfLoan;
    @Builder.Default
    private BigDecimal lendingAmount = BigDecimal.ZERO;
    private long numberOfSaving;
    @Builder.Default
    private BigDecimal savingDepositAmount = BigDecimal.ZERO;
}
