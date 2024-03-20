package vn.edu.ptit.sqa.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
