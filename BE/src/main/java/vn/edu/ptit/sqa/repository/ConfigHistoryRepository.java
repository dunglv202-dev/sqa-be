package vn.edu.ptit.sqa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.ptit.sqa.constant.ConfigType;
import vn.edu.ptit.sqa.entity.config.ConfigHistory;

import java.time.LocalDate;
import java.util.List;

public interface ConfigHistoryRepository extends JpaRepository<ConfigHistory, Integer> {
    boolean existsByConfigTypeAndStartDate(ConfigType type, LocalDate startDate);

    @Query("""
        FROM ConfigHistory c
        WHERE c.status = vn.edu.ptit.sqa.constant.ConfigStatus.PENDING
            AND c.startDate > CURDATE()
        ORDER BY c.startDate ASC
    """)
    List<ConfigHistory> getAllActivePendingConfigs();
}
