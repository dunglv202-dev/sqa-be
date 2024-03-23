package vn.edu.ptit.sqa.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.constant.LoanType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class LoanConfigReq {
    @NotNull(message = "{loan.config.type.required}")
    private LoanType type;

    @Future(message = "{loan.config.start_date.invalid}")
    private LocalDate startDate;

    private List<@Valid LoanConfigDTO> configs;
}
