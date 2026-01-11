package com.moldavets.finalproject.validation.validators;

import com.moldavets.finalproject.validation.PastOrToday;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BirthDateValidator implements ConstraintValidator<PastOrToday, String> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) return true; // NotNull отдельно
        try {
            LocalDate date = LocalDate.parse(value, formatter);
            return !date.isAfter(LocalDate.now());
        } catch (Exception e) {
            return false;
        }
    }
}
