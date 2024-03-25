package vn.edu.ptit.sqa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.ptit.sqa.constant.LoanType;
import vn.edu.ptit.sqa.entity.loan.LoanPurpose;

import java.util.List;

public interface LoanPurposeRepository extends JpaRepository<LoanPurpose, Long> {
    List<LoanPurpose> findAllByLoanType(LoanType type);
}
