package vn.edu.ptit.sqa.entity;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class IdentityCard {
    private String idNumber;
    private LocalDate issueDate;
}
