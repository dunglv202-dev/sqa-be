package vn.edu.ptit.sqa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class UncollectedLoanSummary {
    private Long uncollectedDueLoan;
    private BigDecimal uncollectedAmount;
}
