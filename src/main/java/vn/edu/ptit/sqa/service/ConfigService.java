package vn.edu.ptit.sqa.service;

import jakarta.validation.Valid;
import vn.edu.ptit.sqa.constant.LoanType;
import vn.edu.ptit.sqa.dto.config.*;
import vn.edu.ptit.sqa.model.Pagination;
import vn.edu.ptit.sqa.model.ResultPage;

import java.util.List;

public interface ConfigService {
    void createNewLoanConfig(@Valid LoanConfigReq loanConfigReq);
    void createNewSavingConfig(@Valid SavingConfigReq savingConfigReq);
    void updateConfigReviewResult(Integer configId, ReviewConfigResultDTO reviewConfigResult);
    ResultPage<ConfigHistoryDTO> getAllPendingConfig(Pagination pagination);
    ResultPage<ConfigHistoryDTO> getAllConfigHistory(Pagination pagination);
    List<SavingConfigDTO> getCurrentSavingConfigs();
    List<LoanConfigDTO> getCurrentLoanConfigs(LoanType type);
    DetailConfig<LoanConfigDTO> getLoanConfig(Integer id);
    DetailConfig<SavingConfigDTO> getSavingConfig(Integer id);
}
