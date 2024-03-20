package vn.edu.ptit.sqa.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
public class Saving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    private BigDecimal amount;

    private double yearlyInterestRate;

    private Period term;

    private LocalDate dueDate;

    @CreationTimestamp
    private LocalDate depositDate;

    private LocalDate withdrawDate;

    /* ----------------- SEPARATE ATTRIBUTES AND OPERATIONS ------------------ */

    private void setDueDate(LocalDate dueDate) {}

    public void setTerm(Period term) {
        this.term = term;

        if (this.depositDate != null) {
            this.dueDate = this.depositDate.plus(this.term);
        }
    }

    public void setDepositDate(LocalDate depositTimestamp) {
        this.depositDate = depositTimestamp;

        if (this.term != null) {
            this.dueDate = this.depositDate.plus(this.term);
        }
    }
}
