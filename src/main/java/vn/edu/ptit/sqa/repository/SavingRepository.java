package vn.edu.ptit.sqa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.ptit.sqa.entity.customer.Customer;
import vn.edu.ptit.sqa.entity.saving.Saving;
import vn.edu.ptit.sqa.model.NewSavingSummary;

import java.time.LocalDate;
import java.util.List;

public interface SavingRepository extends JpaRepository<Saving, Long>, JpaSpecificationExecutor<Saving> {
    @Query("""
        SELECT new vn.edu.ptit.sqa.model.NewSavingSummary(COUNT(*), SUM(s.amount))
        FROM Saving s
        WHERE s.depositDate BETWEEN :from AND :to
    """)
    NewSavingSummary summaryForNewSaving(@Param("from") LocalDate from, @Param("to") LocalDate to);

    List<Saving> findAllByCustomer(Customer customer);

    @Query("""
        SELECT COUNT(*)
        FROM Saving s
        WHERE s.withdrawDate >= :from AND s.withdrawDate <= :to
    """)
    int getNumberOfWithdraw(@Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query("""
        SELECT COUNT(*)
        FROM Saving s
        WHERE s.dueDate <= :to AND s.withdrawDate > :to
    """)
    int getDueAccountNotWithdraw(@Param("to") LocalDate to);
}
