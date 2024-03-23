package vn.edu.ptit.sqa.dto.loan;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.constant.ConfigType;
import vn.edu.ptit.sqa.constant.LoanType;
import vn.edu.ptit.sqa.dto.generic.ConfigReq;
import vn.edu.ptit.sqa.entity.config.ConfigHistory;
import vn.edu.ptit.sqa.entity.config.LoanConfig;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LoanConfigReq extends ConfigReq<LoanConfigDTO> {
    @NotNull(message = "{loan.config.type.required}")
    private LoanType type;

    public ConfigHistory toHistoryEntity() {
        ConfigHistory configHistory = super.toHistoryEntity();
        configHistory.setConfigType(this.getConfigType());

        return configHistory;
    }

    @Override
    public List<LoanConfig> toConfigList() {
        List<LoanConfig> loanConfigs = new ArrayList<>();
        configs.forEach((config) -> {
            LoanConfig loanConfig = config.toEntity();
            loanConfig.setConfigHistory(super.getConfigHistory());
            loanConfigs.add(loanConfig);
        });

        return loanConfigs;
    }

    public ConfigType getConfigType() {
        return type == LoanType.SECURED ? ConfigType.SECURED_LOAN : ConfigType.UNSECURED_LOAN;
    }
}
