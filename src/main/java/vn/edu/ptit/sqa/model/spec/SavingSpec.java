package vn.edu.ptit.sqa.model.spec;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import vn.edu.ptit.sqa.entity.customer.Customer_;
import vn.edu.ptit.sqa.entity.customer.IdentityCard_;
import vn.edu.ptit.sqa.entity.saving.Saving;
import vn.edu.ptit.sqa.entity.saving.Saving_;

@Getter
@Setter
public class SavingSpec {
    private String idCardNo;

    private Specification<Saving> hasIdCardNo(String idCardNo) {
        if (idCardNo == null) return Specification.where(null);

        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
            root.get(Saving_.CUSTOMER)
                .get(Customer_.IDENTITY_CARD)
                .get(IdentityCard_.ID_NUMBER),
            idCardNo.concat("%")
        );
    }

    public Specification<Saving> build() {
        return hasIdCardNo(this.idCardNo);
    }
}
