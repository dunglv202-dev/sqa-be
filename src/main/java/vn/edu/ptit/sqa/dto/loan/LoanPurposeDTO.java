package vn.edu.ptit.sqa.dto.loan;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.entity.loan.LoanPurpose;

@Getter
@Setter
public class LoanPurposeDTO {
    private Long id;
    private String label;

    public LoanPurposeDTO(LoanPurpose purpose) {
        this.id = purpose.getId();
        this.label = purpose.getLabel();
    }
}
