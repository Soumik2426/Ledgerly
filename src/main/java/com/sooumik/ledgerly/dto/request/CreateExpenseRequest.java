package com.sooumik.ledgerly.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateExpenseRequest {
    @NotBlank(message = "Title cannot be blanl")
    private String title;

    @NotNull
    @Positive(message = "Amount has to greater than zero")
    private Double amount;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @NotNull(message = "Cannot be empty")
    private LocalDate date;
}
