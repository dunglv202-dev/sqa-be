package vn.edu.ptit.sqa.service.impl;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import vn.edu.ptit.sqa.constant.LoanType;
import vn.edu.ptit.sqa.dto.loan.CustomerLoanDTO;
import vn.edu.ptit.sqa.dto.loan.CustomerLoanListing;
import vn.edu.ptit.sqa.dto.loan.DetailLoan;
import vn.edu.ptit.sqa.dto.loan.LoanDTO;
import vn.edu.ptit.sqa.entity.customer.Customer;
import vn.edu.ptit.sqa.entity.customer.IdentityCard;
import vn.edu.ptit.sqa.entity.customer.Job;
import vn.edu.ptit.sqa.entity.loan.Loan;
import vn.edu.ptit.sqa.entity.loan.LoanPurpose;
import vn.edu.ptit.sqa.exception.CustomerNotExistException;
import vn.edu.ptit.sqa.exception.LoanNotExistException;
import vn.edu.ptit.sqa.model.Pagination;
import vn.edu.ptit.sqa.model.ResultPage;
import vn.edu.ptit.sqa.model.spec.LoanSpec;
import vn.edu.ptit.sqa.repository.CustomerRepository;
import vn.edu.ptit.sqa.repository.LoanRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    @Nested
    class TestGetCustomerLoans {
        @Test
        void customerNotExist() {
            Long customerId = 1L;

            when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

            assertThrows(
                CustomerNotExistException.class,
                () -> loanService.getCustomerLoans(customerId)
            );
        }

        @Test
        void validCustomerWithLoans() {
            Customer customer = new Customer();
            IdentityCard identityCard = new IdentityCard();
            identityCard.setIdNumber("12345");
            customer.setIdentityCard(identityCard);
            customer.setId(1L);
            List<Loan> loans = new ArrayList<>();
            loans.add(new Loan());
            loans.add(new Loan());
            List<CustomerLoanDTO> loanDTOS = loans.stream().map(CustomerLoanDTO::new).toList();

            when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
            when(loanRepository.findAllByCustomer(customer)).thenReturn(loans);

            CustomerLoanListing loanListing = loanService.getCustomerLoans(customer.getId());
            assertIterableEquals(loanDTOS, loanListing.getLoans());
            assertEquals(customer.getId(), loanListing.getCustomer().getId());
            assertEquals(customer.getIdentityCard().getIdNumber(), loanListing.getCustomer().getIdCardNo());
        }
    }

    @Nested
    class TestGetDetailLoan {
        @Test
        void loanNotExist() {
            Long loanId = 1L;

            when(loanRepository.findById(loanId)).thenReturn(Optional.empty());

            assertThrows(LoanNotExistException.class, () -> loanService.getDetailLoan(loanId));
        }

        @Test
        void validLoan() {
            Loan loan = new Loan();
            loan.setId(1L);
            Job job = new Job();
            job.setLabel("Trivial");
            loan.setJob(job);
            LoanPurpose loanPurpose = new LoanPurpose();
            loan.setPurpose(loanPurpose);
            loanPurpose.setLabel("Unknown");
            Customer customer = new Customer();
            IdentityCard identityCard = new IdentityCard();
            identityCard.setIdNumber("12345");
            customer.setId(1L);
            customer.setIdentityCard(identityCard);
            loan.setCustomer(customer);

            when(loanRepository.findById(loan.getId())).thenReturn(Optional.of(loan));

            DetailLoan actual = loanService.getDetailLoan(loan.getId());

            assertEquals(loan.getId(), actual.getId());
            assertEquals(job.getLabel(), actual.getCustomer().getJob());
            assertEquals(loanPurpose.getLabel(), actual.getPurpose());
            assertEquals(loan.getCustomer().getId(), actual.getCustomer().getId());
        }
    }

    @Nested
    class TestGetAllLoans {
        @Test
        void getAllLoan() {
            int TOTAL_PAGES = 10;
            LoanSpec loanSpec = new LoanSpec();
            loanSpec.setTypes(Set.of(LoanType.SECURED));
            loanSpec.setIdCardNo("12345");
            Pagination pagination = new Pagination();
            pagination.setPage(0);
            pagination.setSize(3);
            List<Loan> loans = new ArrayList<>();
            loans.add(mock(RETURNS_DEEP_STUBS));
            loans.add(mock(RETURNS_DEEP_STUBS));
            Page<Loan> loanPage = spy(new PageImpl<>(loans));

            when(loanPage.getTotalPages()).thenReturn(TOTAL_PAGES);
            when(loanRepository.findAll(any(Specification.class), (Pageable) any())).thenReturn(loanPage);

            ResultPage<LoanDTO> resultPage = loanService.getAllLoans(loanSpec, pagination);

            assertEquals(loans.size(), resultPage.getItems().size());
            assertEquals(TOTAL_PAGES, resultPage.getTotalPages());
        }
    }
}