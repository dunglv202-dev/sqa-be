package vn.edu.ptit.sqa.entity.loan;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import vn.edu.ptit.sqa.constant.LoanType;
import vn.edu.ptit.sqa.entity.customer.Customer;
import vn.edu.ptit.sqa.entity.customer.Job;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    private BigDecimal amount;

    private BigDecimal remaining;

    private LocalDate dueDate;

    private double yearlyInterestRate;

    @ManyToOne
    private LoanPurpose purpose;

    private BigDecimal monthlyIncome;

    @ManyToOne
    private Job job;

    @CreationTimestamp
    private LocalDate createdAt;

    @OneToMany
    private List<Collateral> collaterals;

    @Enumerated(EnumType.STRING)
    private LoanType type;
}
