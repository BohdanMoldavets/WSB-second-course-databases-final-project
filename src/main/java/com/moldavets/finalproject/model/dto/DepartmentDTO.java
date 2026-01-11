package com.moldavets.finalproject.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DepartmentDTO {

    private int id;

    @NotNull(message = "Abbreviation is required")
    @Size(max = 3, message = "Abbreviation length must be 3 characters or less")
    private String abbreviation;

    @NotNull(message = "Name is required")
    @Size(min = 1, max = 50, message = "Name must be 1-50 characters")
    private String name;
}
