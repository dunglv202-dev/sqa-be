package vn.edu.ptit.sqa.dto.generic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.constant.ConfigStatus;
import vn.edu.ptit.sqa.entity.config.Config;
import vn.edu.ptit.sqa.entity.config.ConfigHistory;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public abstract class ConfigReq<C> {
    private ConfigHistory configHistory;

    @Future(message = "{saving.config.start_date.invalid}")
    protected LocalDate startDate;

    protected Set<@Valid C> configs;

    public ConfigHistory toHistoryEntity() {
        ConfigHistory configHistory = new ConfigHistory();
        configHistory.setStartDate(this.startDate);
        configHistory.setStatus(ConfigStatus.PENDING);

        this.configHistory = configHistory;

        return configHistory;
    }

    public abstract List<? extends Config> toConfigList();

    private void setConfigHistory(ConfigHistory configHistory) {}

    protected ConfigHistory getConfigHistory() {
        return configHistory;
    }
}
