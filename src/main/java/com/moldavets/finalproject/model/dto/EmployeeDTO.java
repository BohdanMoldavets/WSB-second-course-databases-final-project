package com.moldavets.finalproject.model.dto;

import com.moldavets.finalproject.validation.PastOrToday;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmployeeDTO {

    private int id;

    @NotNull(message = "First name is required")
    @Size(min = 1, max = 50, message = "First name must be 1-50 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name can contain only letters")
    private String firstName;

    @NotNull(message = "Last name is required")
    @Size(min = 1, max = 50, message = "Last name must be 1-50 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name can contain only letters")
    private String lastName;

    @Size(max = 3, message = "Department abbreviation must be 3 characters or less")
    private String department;

    @NotNull(message = "Birthday is required")
    @PastOrToday
    private String birthday;

    private SalaryDTO salary;
    private DateStampDTO dateStamp;
}
