package vn.edu.ptit.sqa.entity.customer;

import jakarta.persistence.*;
import lombok.Data;
import vn.edu.ptit.sqa.constant.Gender;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String lastname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private IdentityCard identityCard;
}
