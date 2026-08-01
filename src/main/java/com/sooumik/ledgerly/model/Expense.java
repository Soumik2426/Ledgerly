package com.sooumik.ledgerly.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Expense {
    private Long id;
    private String title;
    private Double amount;
    private String category;
    private LocalDate date;
}
