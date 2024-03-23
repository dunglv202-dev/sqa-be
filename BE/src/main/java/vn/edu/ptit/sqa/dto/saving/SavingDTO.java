package vn.edu.ptit.sqa.dto.saving;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.entity.saving.Saving;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class SavingDTO {
    private Long id;
    private BigDecimal amount;
    private double yearlyInterestRate;
    private int termInMonth;
    private LocalDate dueDate;
    private LocalDate depositDate;
    private LocalDate withdrawDate;

    public SavingDTO(Saving saving) {
        this.id = saving.getId();
        this.amount = saving.getAmount();
        this.yearlyInterestRate = saving.getYearlyInterestRate();
        this.termInMonth = saving.getTermInMonth();
        this.dueDate = saving.getDueDate();
        this.depositDate = saving.getDepositDate();
        this.withdrawDate = saving.getWithdrawDate();
    }
}
