package vn.edu.ptit.sqa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.ptit.sqa.entity.config.ConfigHistory;
import vn.edu.ptit.sqa.entity.config.LoanConfig;

import java.util.List;

public interface LoanConfigRepository extends JpaRepository<LoanConfig, Integer> {
    List<LoanConfig> findAllByConfigHistory(ConfigHistory lastConfig);
}
