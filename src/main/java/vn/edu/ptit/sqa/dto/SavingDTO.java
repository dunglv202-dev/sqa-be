package vn.edu.ptit.sqa.dto;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.constant.Term;
import vn.edu.ptit.sqa.entity.Saving;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
public class SavingDTO {
    private Long id;
    private BigDecimal amount;
    private double yearlyInterestRate;
    private Term term;
    private LocalDate dueDate;
    private LocalDate depositDate;
    private LocalDate withdrawDate;

    public SavingDTO(Saving saving) {
        this.id = saving.getId();
        this.amount = saving.getAmount();
        this.yearlyInterestRate = saving.getYearlyInterestRate();
        this.term = saving.getTerm();
        this.dueDate = saving.getDueDate();
        this.depositDate = saving.getDepositDate();
        this.withdrawDate = saving.getWithdrawDate();
    }
}
