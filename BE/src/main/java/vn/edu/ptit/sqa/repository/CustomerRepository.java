package vn.edu.ptit.sqa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.ptit.sqa.entity.customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
