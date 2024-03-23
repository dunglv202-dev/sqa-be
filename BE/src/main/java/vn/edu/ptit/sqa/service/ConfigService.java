package vn.edu.ptit.sqa.service;

import jakarta.validation.Valid;
import vn.edu.ptit.sqa.dto.config.ConfigHistoryDTO;
import vn.edu.ptit.sqa.dto.config.LoanConfigReq;
import vn.edu.ptit.sqa.dto.config.ReviewConfigResultDTO;
import vn.edu.ptit.sqa.dto.config.SavingConfigReq;
import vn.edu.ptit.sqa.model.Pagination;
import vn.edu.ptit.sqa.model.ResultPage;

import java.util.List;

public interface ConfigService {
    void changeLoanConfig(@Valid LoanConfigReq loanConfigReq);
    void changeSavingConfig(@Valid SavingConfigReq savingConfigReq);
    void updateConfigReviewResult(Integer configId, ReviewConfigResultDTO reviewConfigResult);
    ResultPage<ConfigHistoryDTO> getAllPendingConfig(Pagination pagination);
    ResultPage<ConfigHistoryDTO> getAllConfigHistory(Pagination pagination);
}
