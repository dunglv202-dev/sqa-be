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
    private BigDecimal lendingAmount;
    private long numberOfSaving;
    private BigDecimal savingDepositAmount;
}
