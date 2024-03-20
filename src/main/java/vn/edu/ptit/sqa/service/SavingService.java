package vn.edu.ptit.sqa.service;

import vn.edu.ptit.sqa.dto.CustomerSavingListing;

public interface SavingService {
    CustomerSavingListing getCustomerSavingAccounts(Long customerId);
}
