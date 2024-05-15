package vn.edu.ptit.sqa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class NewSavingSummary {
    private Long newSaving;
    private BigDecimal totalDepositAmount;
}
