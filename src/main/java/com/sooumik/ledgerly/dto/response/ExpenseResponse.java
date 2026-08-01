package com.sooumik.ledgerly.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseResponse {
    private Long id;
    private String title;
    private Double amount;
    private String category;
    private LocalDate date;
}
