package vn.edu.ptit.sqa.service;

import vn.edu.ptit.sqa.dto.loan.CustomerLoanListing;
import vn.edu.ptit.sqa.dto.loan.DetailLoan;
import vn.edu.ptit.sqa.dto.loan.LoanDTO;
import vn.edu.ptit.sqa.model.Pagination;
import vn.edu.ptit.sqa.model.ResultPage;
import vn.edu.ptit.sqa.model.spec.LoanSpec;

public interface LoanService {
    CustomerLoanListing getCustomerLoans(Long customerId);

    DetailLoan getDetailLoan(Long id);

    ResultPage<LoanDTO> getAllLoans(LoanSpec spec, Pagination pagination);
}
