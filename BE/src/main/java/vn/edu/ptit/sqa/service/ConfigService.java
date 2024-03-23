package vn.edu.ptit.sqa.service;

import jakarta.validation.Valid;
import vn.edu.ptit.sqa.dto.LoanConfigReq;

public interface ConfigService {
    void changeLoanConfig(@Valid LoanConfigReq loanConfigReq);
}
