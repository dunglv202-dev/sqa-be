package vn.edu.ptit.sqa.dto;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.entity.Job;
import vn.edu.ptit.sqa.entity.Loan;
import vn.edu.ptit.sqa.entity.LoanPurpose;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class LoanDTO {
    private Long id;
    private BigDecimal amount;
    private BigDecimal remaining;
    private LocalDate dueDate;
    private double yearlyInterestRate;
    private String purpose;
    private LocalDate createdAt;

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.amount = loan.getAmount();
        this.remaining = loan.getRemaining();
        this.dueDate = loan.getDueDate();
        this.yearlyInterestRate = loan.getYearlyInterestRate();
        this.purpose = loan.getPurpose().getLabel();
        this.createdAt = loan.getCreatedAt();
    }
}
