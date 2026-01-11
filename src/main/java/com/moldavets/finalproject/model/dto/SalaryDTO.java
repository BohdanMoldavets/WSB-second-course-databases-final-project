package com.moldavets.finalproject.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SalaryDTO {

    private int id;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    private Float amount;

    @NotNull(message = "Currency is required")
    @Size(min = 2, max = 3, message = "Currency must be 2-3 letters")
    @Pattern(regexp = "^[A-Z]+$", message = "Currency must be uppercase letters")
    private String currency;

    @NotNull(message = "Employee ID is required")
    private Integer employeeId;
}
