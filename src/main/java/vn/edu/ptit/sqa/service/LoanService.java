package vn.edu.ptit.sqa.service;

import vn.edu.ptit.sqa.dto.CustomerLoanListing;

public interface LoanService {
    CustomerLoanListing getCustomerLoans(Long customerId);
}
