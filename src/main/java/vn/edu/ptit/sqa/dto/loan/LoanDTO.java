package vn.edu.ptit.sqa.dto.loan;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.constant.LoanType;
import vn.edu.ptit.sqa.entity.loan.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class LoanDTO {
    private Long id;
    private String customer;
    private String idCardNo;
    private LocalDate lendingDate;
    private LoanType type;
    private BigDecimal amount;

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.customer = loan.getCustomer().getFullName();
        this.idCardNo = loan.getCustomer().getIdentityCard().getIdNumber();
        this.lendingDate = loan.getCreatedAt();
        this.type = loan.getType();
        this.amount = loan.getAmount();
    }
}
