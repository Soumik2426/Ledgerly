package com.sooumik.ledgerly.dto.response;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseSummaryResponse {

    private Double totalExpenses;
    private Map<String, Double> categoryWiseExpenses;
}
