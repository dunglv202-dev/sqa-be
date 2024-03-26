package vn.edu.ptit.sqa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.ptit.sqa.entity.config.ConfigHistory;
import vn.edu.ptit.sqa.entity.config.SavingConfig;

import java.util.List;

public interface SavingConfigRepository extends JpaRepository<SavingConfig, Integer> {
    List<SavingConfig> findAllByConfigHistory(ConfigHistory configHistory);
}
