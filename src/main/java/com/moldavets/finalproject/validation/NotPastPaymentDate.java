package com.moldavets.finalproject.validation;

import com.moldavets.finalproject.validation.validators.PaymentDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PaymentDateValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotPastPaymentDate {
    String message() default "Payment date must be today or later";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
