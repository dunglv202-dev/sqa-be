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
    private String customer;
    private String idCardNo;
    private LocalDate depositDate;
    private int termInMonth;
    private BigDecimal amount;
    private double yearlyInterestRate;

    public SavingDTO(Saving saving) {
        this.id = saving.getId();
        this.customer = saving.getCustomer().getFullName();
        this.idCardNo = saving.getCustomer().getIdentityCard().getIdNumber();
        this.depositDate = saving.getDepositDate();
        this.termInMonth = saving.getTermInMonth();
        this.amount = saving.getAmount();
        this.yearlyInterestRate = saving.getYearlyInterestRate();
    }
}
