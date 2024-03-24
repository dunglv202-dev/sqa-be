package vn.edu.ptit.sqa.service;

import vn.edu.ptit.sqa.dto.loan.CustomerLoanListing;
import vn.edu.ptit.sqa.dto.loan.DetailLoan;

public interface LoanService {
    CustomerLoanListing getCustomerLoans(Long customerId);

    DetailLoan getDetailLoan(Long id);
}
