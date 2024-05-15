package vn.edu.ptit.sqa.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.ptit.sqa.dto.customer.CustomerDTO;
import vn.edu.ptit.sqa.dto.loan.CustomerLoanListing;
import vn.edu.ptit.sqa.dto.loan.CustomerLoanDTO;
import vn.edu.ptit.sqa.dto.loan.DetailLoan;
import vn.edu.ptit.sqa.dto.loan.LoanDTO;
import vn.edu.ptit.sqa.entity.customer.Customer;
import vn.edu.ptit.sqa.entity.loan.Loan;
import vn.edu.ptit.sqa.exception.ClientVisibleException;
import vn.edu.ptit.sqa.exception.CustomerNotExistException;
import vn.edu.ptit.sqa.exception.LoanNotExistException;
import vn.edu.ptit.sqa.model.Pagination;
import vn.edu.ptit.sqa.model.ResultPage;
import vn.edu.ptit.sqa.model.spec.LoanSpec;
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
            .orElseThrow(() -> new CustomerNotExistException(customerId));

        List<Loan> loans = loanRepository.findAllByCustomer(customer);

        return CustomerLoanListing.builder()
            .customer(new CustomerDTO(customer))
            .loans(loans.stream().map(CustomerLoanDTO::new).toList())
            .build();
    }

    @Override
    public DetailLoan getDetailLoan(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow(LoanNotExistException::new);

        return new DetailLoan(loan);
    }

    @Override
    public ResultPage<LoanDTO> getAllLoans(LoanSpec spec, Pagination pagination) {
        Page<Loan> loans = loanRepository.findAll(spec.build(), PageRequest.of(
            pagination.getPage(),
            pagination.getSize()
        ));

        return ResultPage.<LoanDTO>builder()
            .totalPages(loans.getTotalPages())
            .items(loans.map(LoanDTO::new).toList())
            .build();
    }
}
