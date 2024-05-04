package vn.edu.ptit.sqa.entity.config;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.entity.config.Config;

@Entity
@Getter
@Setter
public class SavingConfig extends Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer termInMonth;

    private double yearlyInterestRate;
}
