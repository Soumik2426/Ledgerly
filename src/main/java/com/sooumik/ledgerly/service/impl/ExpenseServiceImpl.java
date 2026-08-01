package com.sooumik.ledgerly.service.impl;

import com.sooumik.ledgerly.dto.request.CreateExpenseRequest;
import com.sooumik.ledgerly.dto.response.ExpenseResponse;
import com.sooumik.ledgerly.model.Expense;
import com.sooumik.ledgerly.service.ExpenseService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ExpenseServiceImpl implements ExpenseService {

    @Override
    public ExpenseResponse addExpense(CreateExpenseRequest request) {
        return null;
    }

    private final Map<Long, Expense> expenses = new HashMap<>();

    private final AtomicLong idGenerator = new AtomicLong(1);
}
