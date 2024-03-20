package vn.edu.ptit.sqa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class NewLoanSummary {
    private Long newLoan;
    private BigDecimal totalAmount = BigDecimal.ZERO;
}
