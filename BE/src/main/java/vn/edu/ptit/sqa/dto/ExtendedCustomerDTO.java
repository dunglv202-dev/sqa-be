package vn.edu.ptit.sqa.dto;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.entity.Customer;

import java.math.BigDecimal;

@Getter
@Setter
public class ExtendedCustomerDTO extends CustomerDTO {
    protected String job;
    protected BigDecimal monthlyIncome;

    public ExtendedCustomerDTO(Customer customer) {
        super(customer);
    }
}
