package vn.edu.ptit.sqa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.ptit.sqa.entity.customer.Customer;
import vn.edu.ptit.sqa.entity.loan.Loan;
import vn.edu.ptit.sqa.model.LoanPurposeDistribution;
import vn.edu.ptit.sqa.model.LoanTypeDistribution;
import vn.edu.ptit.sqa.model.NewLoanSummary;
import vn.edu.ptit.sqa.model.UncollectedLoanSummary;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long>, JpaSpecificationExecutor<Loan> {
    @Query("""
        SELECT new vn.edu.ptit.sqa.model.NewLoanSummary(COUNT(*), SUM(l.amount))
        FROM Loan l
        WHERE l.createdAt BETWEEN :from AND :to
    """)
    NewLoanSummary summaryForNewLoan(@Param("from") LocalDate from, @Param("to") LocalDate to);

    List<Loan> findAllByCustomer(Customer customer);

    @Query("""
        SELECT new vn.edu.ptit.sqa.model.LoanPurposeDistribution(l.purpose, SUM(l.amount))
        FROM Loan l
        WHERE l.createdAt BETWEEN :from AND :to
        GROUP BY l.purpose
    """)
    List<LoanPurposeDistribution> getLoanDistributionByPurpose(@Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query("""
        SELECT new vn.edu.ptit.sqa.model.LoanTypeDistribution(l.type, SUM(l.amount))
        FROM Loan l
        WHERE l.createdAt BETWEEN :from AND :to
        GROUP BY l.type
    """)
    List<LoanTypeDistribution> getLoanDistributionByType(@Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query("""
        SELECT COUNT(DISTINCT l1.customer)
        FROM Loan l1
        WHERE (l1.createdAt BETWEEN :from AND :to)
            AND NOT EXISTS (SELECT TRUE FROM Loan l2 WHERE l2.customer = l1.customer AND l2.createdAt < :from)
    """)
    int countNewCustomer(@Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query("""
        SELECT new vn.edu.ptit.sqa.model.UncollectedLoanSummary(COUNT(*), SUM(l.remaining))
        FROM Loan l
        WHERE l.dueDate <= :to AND l.remaining > 0
    """)
    UncollectedLoanSummary summaryForUncollectedLoan(@Param("to") LocalDate to);
}
