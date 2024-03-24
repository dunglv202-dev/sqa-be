package vn.edu.ptit.sqa.entity.customer;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class IdentityCard {
    private String idNumber;
    private LocalDate issueDate;
    private String issueBy;
}
