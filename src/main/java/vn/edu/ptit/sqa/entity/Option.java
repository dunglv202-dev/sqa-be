package vn.edu.ptit.sqa.entity;

import jakarta.persistence.*;

@Entity(name = "option")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String label;
}
