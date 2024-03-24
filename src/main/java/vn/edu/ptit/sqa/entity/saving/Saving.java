package vn.edu.ptit.sqa.entity.saving;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import vn.edu.ptit.sqa.entity.customer.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    private Integer termInMonth;

    private LocalDate dueDate;

    @CreationTimestamp
    private LocalDate depositDate;

    private LocalDate withdrawDate;

    /* ----------------- SEPARATE ATTRIBUTES AND OPERATIONS ------------------ */

    private void setDueDate(LocalDate dueDate) {}

    public void setTermInMonth(Integer termInMonth) {
        this.termInMonth = termInMonth;
        this.assignDueDate();
    }

    public void setDepositDate(LocalDate depositTimestamp) {
        this.depositDate = depositTimestamp;
        this.assignDueDate();
    }

    private void assignDueDate() {
        if (this.termInMonth != null && this.depositDate != null) {
            this.dueDate = this.depositDate.plusMonths(termInMonth);
        }
    }
}
