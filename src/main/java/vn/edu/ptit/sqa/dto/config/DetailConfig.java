package vn.edu.ptit.sqa.dto.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.constant.ConfigType;
import vn.edu.ptit.sqa.dto.auth.UserDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class DetailConfig<T> {
    private ConfigType type;
    private UserDTO userRequested;
    private LocalDate startDate;
    private List<T> configs;
}
