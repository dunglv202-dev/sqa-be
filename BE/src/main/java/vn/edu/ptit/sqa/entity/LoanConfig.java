package vn.edu.ptit.sqa.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import vn.edu.ptit.sqa.constant.LoanType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class LoanConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private LoanType type;

    @ManyToOne
    private LoanPurpose purpose;

    private double yearlyInterestRate;

    private BigDecimal limit;

    private LocalDate startDate;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
