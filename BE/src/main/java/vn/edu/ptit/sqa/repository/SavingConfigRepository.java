package vn.edu.ptit.sqa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.ptit.sqa.entity.config.SavingConfig;

public interface SavingConfigRepository extends JpaRepository<SavingConfig, Integer> {
}
