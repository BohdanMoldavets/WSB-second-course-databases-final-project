package com.moldavets.finalproject.validation.validators;

import com.moldavets.finalproject.validation.NotPastPaymentDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PaymentDateValidator implements ConstraintValidator<NotPastPaymentDate, String> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) return true; // NotNull отдельно
        try {
            LocalDate date = LocalDate.parse(value, formatter);
            return !date.isBefore(LocalDate.now());
        } catch (Exception e) {
            return false;
        }
    }
}
