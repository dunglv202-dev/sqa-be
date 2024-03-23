package vn.edu.ptit.sqa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.ptit.sqa.constant.LoanType;
import vn.edu.ptit.sqa.entity.LoanConfig;

import java.time.LocalDate;
import java.util.List;

public interface LoanConfigRepository extends JpaRepository<LoanConfig, Integer> {
    boolean existsByTypeAndStartDate(LoanType type, LocalDate startDate);
}
