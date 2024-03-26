package vn.edu.ptit.sqa.model;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.validator.ReportPeriod;

import java.time.LocalDate;
import java.time.YearMonth;

// TODO: validate this model
@Getter
@Setter
@ReportPeriod
public class Period {
    private Integer month;
    private Integer quarter;
    private Integer year;

    public LocalDate getFrom() {
        int fromMonth = quarter == null ? ((month == null) ? 1 : month) : quarter * 3 - 2;
        return LocalDate.of(year, fromMonth, 1);
    }

    public LocalDate getTo() {
        int toMonth = quarter == null ? ((month == null) ? 12 : month) : quarter * 3;
        return YearMonth.from(LocalDate.of(year, toMonth, 1)).atEndOfMonth();
    }
}
