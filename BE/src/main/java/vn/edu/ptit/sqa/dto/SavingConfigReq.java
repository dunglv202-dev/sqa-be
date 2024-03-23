package vn.edu.ptit.sqa.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;

import java.time.LocalDate;
import java.util.List;

public class SavingConfigReq {
    @Future(message = "{saving.config.start_date.invalid}")
    private LocalDate startDate;

    private List<@Valid SavingConfigDTO> configs;
}
