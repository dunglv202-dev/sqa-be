package vn.edu.ptit.sqa.model.spec;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import vn.edu.ptit.sqa.constant.LoanType;
import vn.edu.ptit.sqa.entity.customer.Customer_;
import vn.edu.ptit.sqa.entity.customer.IdentityCard_;
import vn.edu.ptit.sqa.entity.loan.Loan;
import vn.edu.ptit.sqa.entity.loan.Loan_;

import java.util.Set;

@Getter
@Setter
public class LoanSpec {
    private String idCardNo;
    private Set<LoanType> types;

    private Specification<Loan> hasIdCardNo(String idCardNo) {
        if (idCardNo == null) return Specification.where(null);

        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
            root.get(Loan_.CUSTOMER)
                .get(Customer_.IDENTITY_CARD)
                .get(IdentityCard_.ID_NUMBER),
            idCardNo.concat("%")
        );
    }

    private Specification<Loan> hasTypes(Set<LoanType> types) {
        if (types == null) return Specification.where(null);

        return (root, query, criteriaBuilder) -> root.get(Loan_.TYPE).in(types);
    }

    public Specification<Loan> build() {
        return hasIdCardNo(this.idCardNo).and(hasTypes(this.types));
    }
}
