package vn.edu.ptit.sqa.dto.saving;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.dto.customer.ExtendedCustomerDTO;
import vn.edu.ptit.sqa.entity.saving.Saving;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DetailSaving {
    private ExtendedCustomerDTO customer;
    private BigDecimal amount;
    private LocalDate depositDate;
    private int termInMonth;
    private double yearlyInterestRate;
    private LocalDate dueDate;
    private BigDecimal totalEarned;

    public DetailSaving(Saving saving) {
        this.customer = new ExtendedCustomerDTO(saving.getCustomer());
        this.amount = saving.getAmount();
        this.depositDate = saving.getDepositDate();
        this.termInMonth = saving.getTermInMonth();
        this.yearlyInterestRate = saving.getYearlyInterestRate();
        this.dueDate = saving.getDueDate();
        this.totalEarned = BigDecimal.valueOf((yearlyInterestRate / 12) * termInMonth + 1).multiply(amount);
    }
}
