package com.sooumik.ledgerly.service.impl;

import com.sooumik.ledgerly.dto.request.CreateExpenseRequest;
import com.sooumik.ledgerly.dto.response.ExpenseResponse;
import com.sooumik.ledgerly.dto.response.ExpenseSummaryResponse;
import com.sooumik.ledgerly.model.Expense;
import com.sooumik.ledgerly.service.ExpenseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ExpenseServiceImpl implements ExpenseService {

    //To create an Expense
    @Override
    public ExpenseResponse addExpense(CreateExpenseRequest request) {
        return null;
    }

    //To get all the expenses
    @Override
    public List<ExpenseResponse> getAllExpenses() {
        return List.of();
    }

    //To get expense by category
    @Override
    public List<ExpenseResponse> getExpensesByCategory(String category) {
        return List.of();
    }

    //To get total expense
    @Override
    public ExpenseSummaryResponse getExpenseSummary() {
        return null;
    }

    //To delete an already existing expense
    @Override
    public void deleteExpense(Long expenseId) {

    }

    //Will act as temporary database
    private final Map<Long, Expense> expenses = new HashMap<>();

    //Will generate Unique ID's
    private final AtomicLong idGenerator = new AtomicLong(1);

    private ExpenseResponse mapToResponse(Expense expense) {
        return ExpenseResponse.builder()
                .id(expense.getId())
                .title(expense.getTitle())
                .amount(expense.getAmount())
                .category(expense.getCategory())
                .date(expense.getDate())
                .build();
    }
}
