package vn.edu.ptit.sqa.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ReportPeriodValidator.class)
@Documented
public @interface ReportPeriod {
    String message() default "{period.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
