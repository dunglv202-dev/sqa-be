package vn.edu.ptit.sqa.dto.customer;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.entity.customer.Customer;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerDTO {
    protected String firstname;
    protected String lastname;
    protected String idCardNo;
    protected LocalDate idCardIssueDate;
    protected String idCardIssueBy;

    public CustomerDTO(Customer customer) {
        this.firstname = customer.getFirstname();
        this.lastname = customer.getLastname();

        this.idCardNo = customer.getIdentityCard().getIdNumber();
        this.idCardIssueDate = customer.getIdentityCard().getIssueDate();
        this.idCardIssueBy = customer.getIdentityCard().getIssueBy();
    }
}
