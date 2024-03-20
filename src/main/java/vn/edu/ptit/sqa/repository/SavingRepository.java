package vn.edu.ptit.sqa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.ptit.sqa.entity.Saving;
import vn.edu.ptit.sqa.model.NewLoanSummary;
import vn.edu.ptit.sqa.model.NewSavingSummary;

import java.time.LocalDate;

public interface SavingRepository extends JpaRepository<Saving, Long> {
    @Query("""
        SELECT new vn.edu.ptit.sqa.model.NewSavingSummary(COUNT(*), SUM(s.amount))
        FROM Saving s
        WHERE s.depositDate BETWEEN :from AND :to
    """)
    NewSavingSummary summaryForNewSaving(@Param("from") LocalDate from, @Param("to") LocalDate to);
}
