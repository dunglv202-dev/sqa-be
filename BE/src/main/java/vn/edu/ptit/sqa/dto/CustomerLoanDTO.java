package vn.edu.ptit.sqa.dto;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.entity.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CustomerLoanDTO {
    private Long id;
    private BigDecimal amount;
    private BigDecimal remaining;
    private LocalDate dueDate;
    private double yearlyInterestRate;
    private LocalDate createdAt;

    public CustomerLoanDTO(Loan loan) {
        this.id = loan.getId();
        this.amount = loan.getAmount();
        this.remaining = loan.getRemaining();
        this.dueDate = loan.getDueDate();
        this.yearlyInterestRate = loan.getYearlyInterestRate();
        this.createdAt = loan.getCreatedAt();
    }
}
