package vn.edu.ptit.sqa.service;

import vn.edu.ptit.sqa.dto.saving.CustomerSavingListing;
import vn.edu.ptit.sqa.dto.saving.DetailSaving;
import vn.edu.ptit.sqa.dto.saving.SavingDTO;
import vn.edu.ptit.sqa.model.Pagination;
import vn.edu.ptit.sqa.model.ResultPage;
import vn.edu.ptit.sqa.model.spec.SavingSpec;

public interface SavingService {
    CustomerSavingListing getCustomerSavingAccounts(Long customerId);
    ResultPage<SavingDTO> getAllSavings(SavingSpec spec, Pagination pagination);
    DetailSaving getDetailSaving(Long id);
}
