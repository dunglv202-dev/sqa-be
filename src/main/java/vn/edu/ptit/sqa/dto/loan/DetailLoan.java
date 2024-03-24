package vn.edu.ptit.sqa.dto.loan;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.constant.LoanType;
import vn.edu.ptit.sqa.dto.customer.ExtendedCustomerDTO;
import vn.edu.ptit.sqa.entity.loan.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class DetailLoan {
    private ExtendedCustomerDTO customer;
    private BigDecimal amount;
    private BigDecimal remaining;
    private LocalDate createdAt;
    private LocalDate dueDate;
    private double yearlyInterestRate;
    private String purpose;
    private List<CollateralDTO> collaterals;
    private LoanType type;

    public DetailLoan(Loan loan) {
        this.customer = new ExtendedCustomerDTO(loan.getCustomer());
        this.customer.setJob(loan.getJob().getLabel());
        this.customer.setMonthlyIncome(loan.getMonthlyIncome());
        this.amount = loan.getAmount();
        this.remaining = loan.getRemaining();
        this.createdAt = loan.getCreatedAt();
        this.dueDate = loan.getDueDate();
        this.yearlyInterestRate = loan.getYearlyInterestRate();
        this.purpose = loan.getPurpose().getLabel();
        this.collaterals = loan.getCollaterals() != null
            ? loan.getCollaterals().stream().map(CollateralDTO::new).toList()
            : null;
        this.type = loan.getType();
    }
}
