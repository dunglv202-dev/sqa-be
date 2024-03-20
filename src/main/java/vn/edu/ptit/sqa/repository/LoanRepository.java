package vn.edu.ptit.sqa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.ptit.sqa.entity.Loan;
import vn.edu.ptit.sqa.model.NewLoanSummary;

import java.time.LocalDate;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("""
        SELECT new vn.edu.ptit.sqa.model.NewLoanSummary(COUNT(*), SUM(l.amount))
        FROM Loan l
        WHERE l.createdAt BETWEEN :from AND :to
    """)
    NewLoanSummary summaryForNewLoan(@Param("from") LocalDate from, @Param("to") LocalDate to);
}
