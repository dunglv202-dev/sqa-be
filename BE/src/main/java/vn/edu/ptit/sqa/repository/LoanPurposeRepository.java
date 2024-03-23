package vn.edu.ptit.sqa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.ptit.sqa.entity.LoanPurpose;

import java.util.List;

public interface LoanPurposeRepository extends JpaRepository<LoanPurpose, Long> {
}
