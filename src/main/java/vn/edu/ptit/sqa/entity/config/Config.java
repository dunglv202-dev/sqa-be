package vn.edu.ptit.sqa.entity.config;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Config {
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    protected ConfigHistory configHistory;

    public abstract Integer getId();
    public abstract void setId(Integer id);
}
