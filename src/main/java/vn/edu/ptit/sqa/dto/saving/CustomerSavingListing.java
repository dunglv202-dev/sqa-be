package vn.edu.ptit.sqa.dto.saving;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.dto.customer.CustomerDTO;

import java.util.List;

@Getter
@Setter
@Builder
public class CustomerSavingListing {
    private CustomerDTO customer;
    private List<CustomerSavingDTO> savingAccounts;
}
