package vn.edu.ptit.sqa.service;

import vn.edu.ptit.sqa.dto.CustomerLoanListing;
import vn.edu.ptit.sqa.dto.DetailLoan;

public interface LoanService {
    CustomerLoanListing getCustomerLoans(Long customerId);

    DetailLoan getDetailLoan(Long id);
}
