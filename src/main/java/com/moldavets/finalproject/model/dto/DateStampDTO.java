package com.moldavets.finalproject.model.dto;

import com.moldavets.finalproject.validation.NotPastPaymentDate;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DateStampDTO {

    private int id;

    @NotNull(message = "Employment date is required")
    private String employmentDate;

    @NotNull(message = "Payment date is required")
    @NotPastPaymentDate
    private String paymentDate;

    @NotNull(message = "Employee ID is required")
    private Integer employeeId;
}
