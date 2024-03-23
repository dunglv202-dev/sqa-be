package vn.edu.ptit.sqa.dto.config;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.constant.ConfigType;
import vn.edu.ptit.sqa.dto.auth.UserDTO;
import vn.edu.ptit.sqa.entity.config.ConfigHistory;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ConfigHistoryDTO {
    private Integer id;
    private String summary;
    private ConfigType configType;
    private LocalDate startDate;
    private LocalDateTime createdAt;
    private UserDTO userRequested;

    public ConfigHistoryDTO(ConfigHistory configHistory) {
        this.id = configHistory.getId();
        this.summary = configHistory.getSummary();
        this.configType = configHistory.getConfigType();
        this.startDate = configHistory.getStartDate();
        this.createdAt = configHistory.getCreatedAt();
        this.userRequested = new UserDTO(configHistory.getCreatedBy());
    }
}
