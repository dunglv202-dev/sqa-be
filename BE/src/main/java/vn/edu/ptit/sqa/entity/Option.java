package vn.edu.ptit.sqa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "option")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String label;
}
