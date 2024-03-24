package vn.edu.ptit.sqa.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@DecimalMax(value = "1", message = "{interest_rate.invalid}")
@PositiveOrZero(message = "{interest_rate.invalid}")
@Documented
public @interface InterestRate {
    String message() default "{interest_rate.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
