package vn.edu.ptit.sqa.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.ptit.sqa.dto.CustomerDTO;
import vn.edu.ptit.sqa.dto.CustomerLoanListing;
import vn.edu.ptit.sqa.dto.CustomerLoanDTO;
import vn.edu.ptit.sqa.dto.DetailLoan;
import vn.edu.ptit.sqa.entity.Customer;
import vn.edu.ptit.sqa.entity.Loan;
import vn.edu.ptit.sqa.exception.BusinessException;
import vn.edu.ptit.sqa.repository.CustomerRepository;
import vn.edu.ptit.sqa.repository.LoanRepository;
import vn.edu.ptit.sqa.service.LoanService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final CustomerRepository customerRepository;
    private final LoanRepository loanRepository;

    @Override
    public CustomerLoanListing getCustomerLoans(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("{customer.not_exist} - " + customerId));

        List<Loan> loans = loanRepository.findAllByCustomer(customer);

        return CustomerLoanListing.builder()
            .customer(new CustomerDTO(customer))
            .loans(loans.stream().map(CustomerLoanDTO::new).toList())
            .build();
    }

    @Override
    public DetailLoan getDetailLoan(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow(BusinessException::new);

        return new DetailLoan(loan);
    }
}
