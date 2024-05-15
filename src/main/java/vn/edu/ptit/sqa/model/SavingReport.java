package vn.edu.ptit.sqa.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class SavingReport {
    private long numberOfSavingAccount;
    private BigDecimal depositAmount;
    private int numberOfWithdraw;
    private int dueAccountNotWithdraw;
    private BigDecimal interestAmountToPay;
}
