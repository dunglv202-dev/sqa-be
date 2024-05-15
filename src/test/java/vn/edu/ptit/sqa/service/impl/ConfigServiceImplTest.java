package vn.edu.ptit.sqa.service.impl;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import vn.edu.ptit.sqa.constant.ConfigStatus;
import vn.edu.ptit.sqa.constant.ConfigType;
import vn.edu.ptit.sqa.constant.LoanType;
import vn.edu.ptit.sqa.dto.config.ConfigHistoryDTO;
import vn.edu.ptit.sqa.dto.config.LoanConfigReq;
import vn.edu.ptit.sqa.dto.config.NewLoanConfigDTO;
import vn.edu.ptit.sqa.dto.config.SavingConfigReq;
import vn.edu.ptit.sqa.entity.auth.User;
import vn.edu.ptit.sqa.entity.config.ConfigHistory;
import vn.edu.ptit.sqa.entity.config.ConfigHistory_;
import vn.edu.ptit.sqa.entity.config.LoanConfig;
import vn.edu.ptit.sqa.entity.config.SavingConfig;
import vn.edu.ptit.sqa.entity.loan.LoanPurpose;
import vn.edu.ptit.sqa.exception.ClientVisibleException;
import vn.edu.ptit.sqa.helper.AuthHelper;
import vn.edu.ptit.sqa.model.Pagination;
import vn.edu.ptit.sqa.model.ResultPage;
import vn.edu.ptit.sqa.repository.ConfigHistoryRepository;
import vn.edu.ptit.sqa.repository.LoanConfigRepository;
import vn.edu.ptit.sqa.repository.LoanPurposeRepository;
import vn.edu.ptit.sqa.repository.SavingConfigRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfigServiceImplTest {
    @Mock
    private LoanPurposeRepository loanPurposeRepository;

    @Mock
    private LoanConfigRepository loanConfigRepository;

    @Mock
    private SavingConfigRepository savingConfigRepository;

    @Mock
    private ConfigHistoryRepository configHistoryRepository;

    @Mock
    private AuthHelper authHelper;

    @InjectMocks
    private ConfigServiceImpl configService;

    @Nested
    class TestCreateNewLoanConfig {
        @Test
        void testCreateNewLoanConfig() {
            LoanType loanType = LoanType.SECURED;

            // Arrange
            LoanConfigReq loanConfigReq = spy(new LoanConfigReq());
            loanConfigReq.setType(loanType);
            loanConfigReq.setConfigs(Set.of(new NewLoanConfigDTO()));
            User user = new User();

            List<LoanConfig> loanConfigs = new ArrayList<>();
            LoanConfig loanConfig = new LoanConfig();
            LoanPurpose purpose = new LoanPurpose();
            purpose.setId(1L);
            loanConfig.setPurpose(purpose);
            loanConfigs.add(loanConfig);

            List<LoanPurpose> loanPurposes = new ArrayList<>();
            LoanPurpose loanPurpose = new LoanPurpose();
            loanPurpose.setId(1L);
            loanPurposes.add(loanPurpose);

            when(authHelper.getSignedUser()).thenReturn(user);
            when(loanPurposeRepository.findAllByLoanType(loanType)).thenReturn(loanPurposes);
            when(loanConfigReq.toConfigList()).thenReturn(loanConfigs);

            // Act
            assertDoesNotThrow(() -> configService.createNewLoanConfig(loanConfigReq));

            // Assert
            verify(loanConfigRepository, times(1)).saveAll(loanConfigs);
        }
    }

    @Nested
    class TestCreateNewSavingConfig {
        @Test
        void testCreateNewSavingConfig() {
            // Arrange
            SavingConfigReq savingConfigReq = spy(new SavingConfigReq());
            savingConfigReq.setConfigs(Set.of());
            User user = new User();

            List<SavingConfig> savingConfigs = new ArrayList<>();
            SavingConfig savingConfig = new SavingConfig();
            savingConfigs.add(savingConfig);

            when(authHelper.getSignedUser()).thenReturn(user);
            doReturn(savingConfigs).when(savingConfigReq).toConfigList();

            // Act
            assertDoesNotThrow(() -> configService.createNewSavingConfig(savingConfigReq));

            // Assert
            verify(savingConfigRepository, times(1)).saveAll(savingConfigs);
        }

        @Test
        void testCreateNewSavingConfig_ThrowsClientVisibleException() {
            // Arrange
            SavingConfigReq savingConfigReq = spy(new SavingConfigReq());
            User user = new User();

            List<SavingConfig> savingConfigs = new ArrayList<>();
            SavingConfig savingConfig = new SavingConfig();
            savingConfigs.add(savingConfig);

            when(authHelper.getSignedUser()).thenReturn(user);
            doReturn(savingConfigs).when(savingConfigReq).toConfigList();

            Exception e = assertThrows(ClientVisibleException.class, () -> configService.createNewSavingConfig(savingConfigReq));
            assertEquals("{saving.config.term.same_set}", e.getMessage());
        }
    }

    @Nested
    class TestUpdateConfigReviewResult {
    }

    @Nested
    class TestGetAllPendingConfig {
    }

    @Nested
    class TestGetCurrentSavingConfigs {
    }

    @Nested
    class TestGetCurrentLoanConfigs {
    }

    @Nested
    class TestGetLoanConfig {
    }

    @Nested
    class TestGetSavingConfig {
    }
}