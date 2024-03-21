package vn.edu.ptit.sqa.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.edu.ptit.sqa.model.Period;

public class ReportPeriodValidator implements ConstraintValidator<ReportPeriod, Period> {
    @Override
    public boolean isValid(Period period, ConstraintValidatorContext context) {
        Integer year = period.getYear();
        Integer quarter = period.getQuarter();
        Integer month = period.getMonth();
        String error = null;

        if (year == null) {
            error = "{report.period.year.required}";
        } else if (quarter != null && (quarter < 1 || quarter > 4)) {
            error = "{report.period.quarter.invalid}";
        } else if (month != null && (month < 1 || month > 12)) {
            error = "{report.period.month.invalid}";
        }

        if (error != null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(error).addConstraintViolation();
            return false;
        }

        return true;
    }
}
