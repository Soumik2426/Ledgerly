package com.sooumik.ledgerly.service;

import com.sooumik.ledgerly.dto.request.CreateExpenseRequest;
import com.sooumik.ledgerly.dto.response.ExpenseResponse;
import com.sooumik.ledgerly.dto.response.ExpenseSummaryResponse;

import java.util.List;

public interface ExpenseService {
    //To create an Expense
    ExpenseResponse addExpense(CreateExpenseRequest request);

    //To get all the expenses
    List<ExpenseResponse> getAllExpenses();

    //To get expense by category
    List<ExpenseResponse> getExpensesByCategory(String category);

    //To get summary of Expense
    ExpenseSummaryResponse getExpenseSummary();

    //To delete an already existing expense
    void deleteExpense(Long expenseId);
}
