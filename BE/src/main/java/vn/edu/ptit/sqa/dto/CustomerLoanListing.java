package vn.edu.ptit.sqa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CustomerLoanListing {
    private CustomerDTO customer;
    private List<CustomerLoanDTO> loans;
}
