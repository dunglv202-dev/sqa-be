package vn.edu.ptit.sqa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class CustomerLoanListing {
    private CustomerDTO customer;
    private List<LoanDTO> loans;
}
