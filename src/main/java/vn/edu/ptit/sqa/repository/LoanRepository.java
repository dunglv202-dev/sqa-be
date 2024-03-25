package vn.edu.ptit.sqa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.ptit.sqa.entity.customer.Customer;
import vn.edu.ptit.sqa.entity.loan.Loan;
import vn.edu.ptit.sqa.model.LoanPurposeDistribution;
import vn.edu.ptit.sqa.model.NewLoanSummary;

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
}
