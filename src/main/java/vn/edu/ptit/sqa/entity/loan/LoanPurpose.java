package vn.edu.ptit.sqa.entity.loan;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.constant.LoanType;
import vn.edu.ptit.sqa.entity.Option;

@Entity
@Getter
@Setter
public class LoanPurpose extends Option {
    @Enumerated(EnumType.STRING)
    private LoanType loanType;
}
