package com.moldavets.finalproject.validation;

import com.moldavets.finalproject.validation.validators.BirthDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BirthDateValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PastOrToday {
    String message() default "Date must be in the past or today";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}