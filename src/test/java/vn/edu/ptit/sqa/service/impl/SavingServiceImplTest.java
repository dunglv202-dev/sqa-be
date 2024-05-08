package vn.edu.ptit.sqa.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import vn.edu.ptit.sqa.dto.saving.CustomerSavingListing;
import vn.edu.ptit.sqa.dto.saving.DetailSaving;
import vn.edu.ptit.sqa.dto.saving.SavingDTO;
import vn.edu.ptit.sqa.entity.customer.Customer;
import vn.edu.ptit.sqa.entity.customer.IdentityCard;
import vn.edu.ptit.sqa.entity.saving.Saving;
import vn.edu.ptit.sqa.exception.ClientVisibleException;
import vn.edu.ptit.sqa.model.Pagination;
import vn.edu.ptit.sqa.model.ResultPage;
import vn.edu.ptit.sqa.model.spec.SavingSpec;
import vn.edu.ptit.sqa.repository.CustomerRepository;
import vn.edu.ptit.sqa.repository.SavingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SavingServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private SavingRepository savingRepository;

    @InjectMocks
    private SavingServiceImpl savingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomerSavingAccounts() {
        Long customerId = 1L;

        Customer customer = new Customer();
        IdentityCard identityCard = new IdentityCard();
        identityCard.setIdNumber("12345");
        customer.setIdentityCard(identityCard);
        customer.setId(customerId);

        List<Saving> savingAccounts = new ArrayList<>();
        savingAccounts.add(mock(RETURNS_MOCKS));
        savingAccounts.add(mock(RETURNS_MOCKS));

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(savingRepository.findAllByCustomer(customer)).thenReturn(savingAccounts);

        CustomerSavingListing result = savingService.getCustomerSavingAccounts(customerId);

        assertEquals(customerId, result.getCustomer().getId());
        assertEquals(savingAccounts.size(), result.getSavingAccounts().size());
    }

    @Test
    void testGetAllSavings() {
        int TOTAL_PAGES = 10;
        SavingSpec spec = new SavingSpec();
        Pagination pagination = new Pagination();
        pagination.setPage(0);
        pagination.setSize(3);
        List<Saving> savings = new ArrayList<>();
        savings.add(mock(RETURNS_MOCKS));
        savings.add(mock(RETURNS_MOCKS));
        Page<Saving> savingPage = spy(new PageImpl<>(savings));

        when(savingPage.getTotalPages()).thenReturn(TOTAL_PAGES);
        when(savingRepository.findAll(eq(spec.build()), (Pageable) any())).thenReturn(savingPage);

        ResultPage<SavingDTO> result = savingService.getAllSavings(spec, pagination);

        assertEquals(savings.size(), result.getItems().size());
    }

    @Test
    void testGetDetailSaving() {
        Long savingId = 1L;

        Saving saving = new Saving();
        Customer customer = new Customer();
        IdentityCard identityCard = new IdentityCard();
        identityCard.setIdNumber("12345");
        customer.setIdentityCard(identityCard);
        customer.setId(1L);
        saving.setId(savingId);
        saving.setCustomer(customer);

        when(savingRepository.findById(savingId)).thenReturn(Optional.of(saving));

        DetailSaving result = savingService.getDetailSaving(savingId);

        assertEquals(savingId, result.getId());
    }

    @Test
    void testGetCustomerSavingAccounts_CustomerNotExist() {
        Long customerId = 1L;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(
            RuntimeException.class,
            () -> savingService.getCustomerSavingAccounts(customerId)
        );
    }

    @Test
    void testGetDetailSaving_SavingNotExist() {
        Long savingId = 1L;

        when(savingRepository.findById(savingId)).thenReturn(Optional.empty());

        assertThrows(
            ClientVisibleException.class,
            () -> savingService.getDetailSaving(savingId)
        );
    }
}