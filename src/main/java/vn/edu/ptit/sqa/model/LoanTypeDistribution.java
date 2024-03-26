package vn.edu.ptit.sqa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.constant.LoanType;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class LoanTypeDistribution {
    private LoanType type;
    private BigDecimal totalAmount;
}
