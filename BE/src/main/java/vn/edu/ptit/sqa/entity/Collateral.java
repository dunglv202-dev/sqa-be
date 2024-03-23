package vn.edu.ptit.sqa.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Map;

@Entity
@Data
public class Collateral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal value;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, String> details;

    @ManyToOne
    private Loan loan;
}
