package vn.edu.ptit.sqa.service;

import vn.edu.ptit.sqa.dto.saving.CustomerSavingListing;

public interface SavingService {
    CustomerSavingListing getCustomerSavingAccounts(Long customerId);
}
