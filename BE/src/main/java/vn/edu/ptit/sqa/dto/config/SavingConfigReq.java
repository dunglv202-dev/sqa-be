package vn.edu.ptit.sqa.dto.config;

import vn.edu.ptit.sqa.constant.ConfigType;
import vn.edu.ptit.sqa.entity.config.ConfigHistory;
import vn.edu.ptit.sqa.entity.config.SavingConfig;

import java.util.ArrayList;
import java.util.List;

public class SavingConfigReq extends ConfigReq<SavingConfigDTO> {
    public ConfigHistory toHistoryEntity() {
        ConfigHistory configHistory = super.toHistoryEntity();
        configHistory.setConfigType(ConfigType.SAVING);

        return configHistory;
    }

    @Override
    public List<SavingConfig> toConfigList() {
        List<SavingConfig> savingConfigs = new ArrayList<>();

        configs.forEach((config) -> {
            SavingConfig savingConfig = config.toEntity();
            savingConfig.setConfigHistory(super.getConfigHistory());
            savingConfigs.add(savingConfig);
        });

        return savingConfigs;
    }
}
