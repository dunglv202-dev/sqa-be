package vn.edu.ptit.sqa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.ptit.sqa.entity.config.LoanConfig;

public interface LoanConfigRepository extends JpaRepository<LoanConfig, Integer> {
}
