package vn.edu.ptit.sqa.dto;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.entity.Customer;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerDTO {
    private String firstname;
    private String lastname;
    private String idCardNo;
    private LocalDate idCardIssueDate;
    private String idCardIssueBy;

    public CustomerDTO(Customer customer) {
        this.firstname = customer.getFirstname();
        this.lastname = customer.getLastname();

        this.idCardNo = customer.getIdentityCard().getIdNumber();
        this.idCardIssueDate = customer.getIdentityCard().getIssueDate();
        this.idCardIssueBy = customer.getIdentityCard().getIssueBy();
    }
}
