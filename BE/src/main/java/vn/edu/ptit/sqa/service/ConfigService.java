package vn.edu.ptit.sqa.service;

import jakarta.validation.Valid;
import vn.edu.ptit.sqa.dto.loan.LoanConfigReq;
import vn.edu.ptit.sqa.dto.saving.SavingConfigReq;

public interface ConfigService {
    void changeLoanConfig(@Valid LoanConfigReq loanConfigReq);
    void changeSavingConfig(@Valid SavingConfigReq savingConfigReq);
}
