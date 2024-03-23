package vn.edu.ptit.sqa.entity.config;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import vn.edu.ptit.sqa.constant.ConfigStatus;
import vn.edu.ptit.sqa.constant.ConfigType;
import vn.edu.ptit.sqa.entity.auth.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class ConfigHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ConfigType configType;

    private LocalDate startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;

    @Enumerated(EnumType.STRING)
    private ConfigStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
