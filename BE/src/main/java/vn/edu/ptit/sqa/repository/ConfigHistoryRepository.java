package vn.edu.ptit.sqa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.ptit.sqa.constant.ConfigType;
import vn.edu.ptit.sqa.entity.config.ConfigHistory;

import java.time.LocalDate;

public interface ConfigHistoryRepository extends JpaRepository<ConfigHistory, Integer> {
    boolean existsByConfigTypeAndStartDate(ConfigType type, LocalDate startDate);
}
