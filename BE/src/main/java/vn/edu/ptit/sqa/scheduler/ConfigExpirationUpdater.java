package vn.edu.ptit.sqa.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.ptit.sqa.repository.ConfigHistoryRepository;

@Component
@RequiredArgsConstructor
public class ConfigExpirationUpdater {
    private final ConfigHistoryRepository configHistoryRepository;

    @Scheduled(cron = "@midnight")
    @Transactional
    public void updateExpiredPendingConfig() {
        configHistoryRepository.updateAllExpiredPendingConfigs();
    }
}
