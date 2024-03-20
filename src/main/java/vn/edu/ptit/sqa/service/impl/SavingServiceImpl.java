package vn.edu.ptit.sqa.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.ptit.sqa.dto.CustomerDTO;
import vn.edu.ptit.sqa.dto.CustomerSavingListing;
import vn.edu.ptit.sqa.dto.SavingDTO;
import vn.edu.ptit.sqa.entity.Customer;
import vn.edu.ptit.sqa.entity.Saving;
import vn.edu.ptit.sqa.repository.CustomerRepository;
import vn.edu.ptit.sqa.repository.SavingRepository;
import vn.edu.ptit.sqa.service.SavingService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SavingServiceImpl implements SavingService {
    private final CustomerRepository customerRepository;
    private final SavingRepository savingRepository;

    @Override
    public CustomerSavingListing getCustomerSavingAccounts(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("{customer.not_exist} - " + customerId));

        List<Saving> savingAccounts = savingRepository.findAllByCustomer(customer);

        return CustomerSavingListing.builder()
            .customer(new CustomerDTO(customer))
            .savingAccounts(savingAccounts.stream().map(SavingDTO::new).toList())
            .build();
    }
}
