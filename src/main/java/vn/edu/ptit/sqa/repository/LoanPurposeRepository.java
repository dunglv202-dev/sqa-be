package vn.edu.ptit.sqa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.ptit.sqa.entity.loan.LoanPurpose;

public interface LoanPurposeRepository extends JpaRepository<LoanPurpose, Long> {
}
