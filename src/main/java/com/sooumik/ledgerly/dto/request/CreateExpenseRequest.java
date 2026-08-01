package com.sooumik.ledgerly.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class CreateExpenseRequest {
    @NotBlank
    private String title;

    @NotNull
    @Positive
    private Double amount;

    @NotBlank
    private String category;

    @NotNull
    private LocalDate date;
}
