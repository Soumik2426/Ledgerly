package com.sooumik.ledgerly.service.impl;

import com.sooumik.ledgerly.dto.request.CreateExpenseRequest;
import com.sooumik.ledgerly.dto.response.ExpenseResponse;
import com.sooumik.ledgerly.dto.response.ExpenseSummaryResponse;
import com.sooumik.ledgerly.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseServiceImplTest {
    private ExpenseServiceImpl expenseService;

    @BeforeEach
    void setUp() {
        expenseService = new ExpenseServiceImpl();
    }

    //To add expense
    @Test
    void shouldAddExpenseSuccessfully() {

        CreateExpenseRequest request = CreateExpenseRequest.builder()
                .title("Pizza")
                .amount(499.99)
                .category("Food")
                .date(LocalDate.of(2026, 8, 1))
                .build();

        ExpenseResponse response = expenseService.addExpense(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Pizza", response.getTitle());
        assertEquals(499.99, response.getAmount());
        assertEquals("Food", response.getCategory());
        assertEquals(LocalDate.of(2026, 8, 1), response.getDate());
    }

    //To get all expenses
    @Test
    void shouldReturnAllExpenses() {

        expenseService.addExpense(CreateExpenseRequest.builder()
                .title("Pizza")
                .amount(499.99)
                .category("Food")
                .date(LocalDate.of(2026, 8, 1))
                .build());

        expenseService.addExpense(CreateExpenseRequest.builder()
                .title("Netflix")
                .amount(649.00)
                .category("Entertainment")
                .date(LocalDate.of(2026, 8, 2))
                .build());

        List<ExpenseResponse> expenses = expenseService.getAllExpenses();

        assertEquals(2, expenses.size());
    }

    //To Get Expense By Category
    @Test
    void shouldReturnExpensesByCategory() {

        expenseService.addExpense(CreateExpenseRequest.builder()
                .title("Pizza")
                .amount(499.99)
                .category("Food")
                .date(LocalDate.now())
                .build());

        expenseService.addExpense(CreateExpenseRequest.builder()
                .title("Burger")
                .amount(250.00)
                .category("Food")
                .date(LocalDate.now())
                .build());

        List<ExpenseResponse> expenses =
                expenseService.getExpensesByCategory("Food");

        assertEquals(2, expenses.size());
    }

    //To check in case Category doesn't exist
    @Test
    void shouldThrowExceptionWhenCategoryDoesNotExist() {

        assertThrows(
                ResourceNotFoundException.class,
                () -> expenseService.getExpensesByCategory("Travel")
        );
    }

    //To search Expense
    @Test
    void shouldSearchExpensesSuccessfully() {

        expenseService.addExpense(CreateExpenseRequest.builder()
                .title("Pizza")
                .amount(499.99)
                .category("Food")
                .date(LocalDate.now())
                .build());

        List<ExpenseResponse> result =
                expenseService.searchExpenses("Pizza");

        assertEquals(1, result.size());
    }

    //To get the total expense
    @Test
    void shouldReturnExpenseSummary() {

        expenseService.addExpense(CreateExpenseRequest.builder()
                .title("Pizza")
                .amount(500.00)
                .category("Food")
                .date(LocalDate.now())
                .build());

        ExpenseSummaryResponse summary =
                expenseService.getExpenseSummary();

        assertEquals(500.00, summary.getTotalExpenses());
    }

    //To get the monthly Expense
    @Test
    void shouldReturnMonthlySummary() {

        expenseService.addExpense(CreateExpenseRequest.builder()
                .title("Pizza")
                .amount(500.00)
                .category("Food")
                .date(LocalDate.of(2026, 8, 1))
                .build());

        ExpenseSummaryResponse summary =
                expenseService.getMonthlySummary(2026, 8);

        assertEquals(500.00, summary.getTotalExpenses());
    }

    //To delete an expense
    @Test
    void shouldDeleteExpenseSuccessfully() {

        ExpenseResponse response =
                expenseService.addExpense(CreateExpenseRequest.builder()
                        .title("Pizza")
                        .amount(500.00)
                        .category("Food")
                        .date(LocalDate.now())
                        .build());

        expenseService.deleteExpense(response.getId());

        assertThrows(
                ResourceNotFoundException.class,
                () -> expenseService.getExpensesByCategory("Food")
        );
    }

    //To check in case of deleting a non-existing Expense
    @Test
    void shouldThrowExceptionWhenDeletingNonExistingExpense() {

        assertThrows(
                ResourceNotFoundException.class,
                () -> expenseService.deleteExpense(100L)
        );
    }

    //To check in case monthly expense not found
    @Test
    void shouldThrowExceptionWhenMonthlySummaryDoesNotExist() {

        assertThrows(
                ResourceNotFoundException.class,
                () -> expenseService.getMonthlySummary(2026, 8)
        );
    }
}
