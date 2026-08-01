package com.sooumik.ledgerly.service;

import com.sooumik.ledgerly.dto.request.CreateExpenseRequest;
import com.sooumik.ledgerly.dto.response.ExpenseResponse;

public interface ExpenseService {
    ExpenseResponse addExpense(CreateExpenseRequest request);
}
