package vn.edu.ptit.sqa.entity.config;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.entity.loan.LoanPurpose;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class LoanConfig extends Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private LoanPurpose purpose;

    private double yearlyInterestRate;

    private BigDecimal limit;
}
