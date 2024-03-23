package vn.edu.ptit.sqa.dto.loan;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.dto.customer.CustomerDTO;

import java.util.List;

@Getter
@Setter
@Builder
public class CustomerLoanListing {
    private CustomerDTO customer;
    private List<CustomerLoanDTO> loans;
}
