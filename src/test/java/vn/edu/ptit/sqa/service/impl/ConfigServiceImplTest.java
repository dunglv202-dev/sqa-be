package vn.edu.ptit.sqa.service.impl;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import vn.edu.ptit.sqa.constant.ConfigStatus;
import vn.edu.ptit.sqa.constant.LoanType;
import vn.edu.ptit.sqa.dto.config.LoanConfigReq;
import vn.edu.ptit.sqa.entity.auth.User;
import vn.edu.ptit.sqa.entity.config.ConfigHistory;
import vn.edu.ptit.sqa.entity.config.LoanConfig;
import vn.edu.ptit.sqa.exception.ClientVisibleException;
import vn.edu.ptit.sqa.helper.AuthHelper;
import vn.edu.ptit.sqa.repository.LoanConfigRepository;
import vn.edu.ptit.sqa.repository.LoanPurposeRepository;
import vn.edu.ptit.sqa.repository.SavingConfigRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConfigServiceImplTest {
    @Mock
    private LoanPurposeRepository loanPurposeRepository;

    @Mock
    private LoanConfigRepository loanConfigRepository;

    @Mock
    private ConfigHistoryRepository configHistoryRepository;

    @Mock
    private SavingConfigRepository savingConfigRepository;

    @Mock
    private AuthHelper authHelper;

    @InjectMocks
    private ConfigServiceImpl configService;

    @Nested
    class TestCreateNewLoanConfig {
        @Test
        void testCreateNewLoanConfig() {
            // Arrange
            LoanConfigReq loanConfigReq = new LoanConfigReq();
            loanConfigReq.setType(LoanType.SECURED);
            loanConfigReq.setPurposeIds(List.of(1L, 2L, 3L));

            User user = new User();
            user.setId(1L);

            when(authHelper.getSignedUser()).thenReturn(user);
            when(loanPurposeRepository.findAllByLoanType(LoanType.SECURED)).thenReturn(getLoanPurposeList());
            when(loanConfigRepository.saveAll(anyList())).thenReturn(getLoanConfigList());

            // Act
            assertDoesNotThrow(() -> configService.createNewLoanConfig(loanConfigReq));

            // Assert
            verify(authHelper, times(1)).getSignedUser();
            verify(loanPurposeRepository, times(1)).findAllByLoanType(LoanType.SECURED);
            verify(loanConfigRepository, times(1)).saveAll(anyList());
        }

        @Test
        void testCreateNewLoanConfig_InvalidPurposeSet() {
            // Arrange
            LoanConfigReq loanConfigReq = new LoanConfigReq();
            loanConfigReq.setType(LoanType.SECURED);
            loanConfigReq.setPurposeIds(List.of(1L, 2L, 4L));

            User user = new User();
            user.setId(1L);

            when(authHelper.getSignedUser()).thenReturn(user);
            when(loanPurposeRepository.findAllByLoanType(LoanType.SECURED)).thenReturn(getLoanPurposeList());

            // Act & Assert
            assertThrows(ClientVisibleException.class, () -> configService.createNewLoanConfig(loanConfigReq));

            verify(authHelper, times(1)).getSignedUser();
            verify(loanPurposeRepository, times(1)).findAllByLoanType(LoanType.SECURED);
            verify(loanConfigRepository, never()).saveAll(anyList());
        }

        private List<LoanConfig> getLoanConfigList() {
            List<LoanConfig> loanConfigs = new ArrayList<>();
            LoanConfig loanConfig1 = new LoanConfig();
            loanConfig1.setId(1L);
            loanConfig1.setPurposeId(1L);
            loanConfig1.setType(LoanType.SECURED);

            LoanConfig loanConfig2 = new LoanConfig();
            loanConfig2.setId(2L);
            loanConfig2.setPurposeId(2L);
            loanConfig2.setType(LoanType.SECURED);

            LoanConfig loanConfig3 = new LoanConfig();
            loanConfig3.setId(3L);
            loanConfig3.setPurposeId(3L);
            loanConfig3.setType(LoanType.SECURED);

            loanConfigs.add(loanConfig1);
            loanConfigs.add(loanConfig2);
            loanConfigs.add(loanConfig3);

            return loanConfigs;
        }

        private List<Long> getLoanPurposeList() {
            return List.of(1L, 2L, 3L);
        }
    }
}
