package vn.edu.ptit.sqa.dto.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "{config.summary.required}")
    @NotBlank(message = "{config.summary.required}")
    protected String summary;

    @Future(message = "{config.start_date.invalid}")
    protected LocalDate startDate;

    protected Set<@Valid C> configs;

    public ConfigHistory toHistoryEntity() {
        ConfigHistory configHistory = new ConfigHistory();
        configHistory.setSummary(this.summary);
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
