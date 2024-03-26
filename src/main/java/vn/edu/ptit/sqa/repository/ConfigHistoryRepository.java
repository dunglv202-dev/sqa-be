package vn.edu.ptit.sqa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.ptit.sqa.constant.ConfigStatus;
import vn.edu.ptit.sqa.constant.ConfigType;
import vn.edu.ptit.sqa.entity.config.ConfigHistory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ConfigHistoryRepository extends JpaRepository<ConfigHistory, Integer> {
    boolean existsByConfigTypeAndStartDate(ConfigType type, LocalDate startDate);

    Page<ConfigHistory> findAllByStatusIn(List<ConfigStatus> statuses, Pageable pageable);

    @Modifying
    @Query("""
        UPDATE ConfigHistory c
        SET c.status = vn.edu.ptit.sqa.constant.ConfigStatus.EXPIRED
        WHERE c.status = vn.edu.ptit.sqa.constant.ConfigStatus.PENDING
            AND c.startDate <= CURDATE()
    """)
    void updateAllExpiredPendingConfigs();

    @Query("""
        FROM ConfigHistory c
        WHERE c.configType = :configType
            AND c.status = vn.edu.ptit.sqa.constant.ConfigStatus.APPROVED
        ORDER BY c.updatedAt DESC
    """)
    Optional<ConfigHistory> findLatestApprovedConfig(@Param("configType") ConfigType configType);
}
