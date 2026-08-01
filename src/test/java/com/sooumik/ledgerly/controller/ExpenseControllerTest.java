package com.sooumik.ledgerly.controller;

import com.sooumik.ledgerly.dto.request.CreateExpenseRequest;
import com.sooumik.ledgerly.dto.response.ExpenseResponse;
import com.sooumik.ledgerly.dto.response.ExpenseSummaryResponse;
import com.sooumik.ledgerly.service.ExpenseService;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpenseController.class)
public class ExpenseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ExpenseService expenseService;

    @Autowired
    private ObjectMapper objectMapper;

    //To add expense
    @Test
    void shouldAddExpenseSuccessfully() throws Exception {

        CreateExpenseRequest request = CreateExpenseRequest.builder()
                .title("Pizza")
                .amount(499.99)
                .category("Food")
                .date(LocalDate.of(2026,8,1))
                .build();

        ExpenseResponse response = ExpenseResponse.builder()
                .id(1L)
                .title("Pizza")
                .amount(499.99)
                .category("Food")
                .date(LocalDate.of(2026,8,1))
                .build();

        when(expenseService.addExpense(any(CreateExpenseRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.title").value("Pizza"))
                .andExpect(jsonPath("$.data.category").value("Food"));
    }

    //To get all the expenses
    @Test
    void shouldReturnAllExpenses() throws Exception {

        List<ExpenseResponse> expenses = List.of(
                ExpenseResponse.builder()
                        .id(1L)
                        .title("Pizza")
                        .amount(499.99)
                        .category("Food")
                        .date(LocalDate.of(2026, 8, 1))
                        .build(),

                ExpenseResponse.builder()
                        .id(2L)
                        .title("Movie")
                        .amount(350.00)
                        .category("Entertainment")
                        .date(LocalDate.of(2026, 8, 2))
                        .build()
        );

        when(expenseService.getAllExpenses())
                .thenReturn(expenses);

        mockMvc.perform(get("/api/v1/expenses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].title").value("Pizza"))
                .andExpect(jsonPath("$.data[1].title").value("Movie"));
    }

    //To get expense By Category
    @Test
    void shouldReturnExpensesByCategory() throws Exception {

        List<ExpenseResponse> expenses = List.of(
                ExpenseResponse.builder()
                        .id(1L)
                        .title("Pizza")
                        .amount(499.99)
                        .category("Food")
                        .date(LocalDate.of(2026,8,1))
                        .build()
        );

        when(expenseService.getExpensesByCategory("Food"))
                .thenReturn(expenses);

        mockMvc.perform(get("/api/v1/expenses/category/Food"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].category").value("Food"));
    }

    //To search by Expense
    @Test
    void shouldSearchExpensesSuccessfully() throws Exception {

        List<ExpenseResponse> expenses = List.of(
                ExpenseResponse.builder()
                        .id(1L)
                        .title("Pizza")
                        .amount(499.99)
                        .category("Food")
                        .date(LocalDate.now())
                        .build()
        );

        when(expenseService.searchExpenses("Pizza"))
                .thenReturn(expenses);

        mockMvc.perform(get("/api/v1/expenses/search/Pizza"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].title").value("Pizza"));
    }

    //To get the total expense
    @Test
    void shouldReturnExpenseSummary() throws Exception {

        ExpenseSummaryResponse response =
                ExpenseSummaryResponse.builder()
                        .totalExpenses(500.0)
                        .categoryWiseExpenses(Map.of("Food", 500.0))
                        .build();

        when(expenseService.getExpenseSummary())
                .thenReturn(response);

        mockMvc.perform(get("/api/v1/expenses/summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalExpenses").value(500.0))
                .andExpect(jsonPath("$.data.categoryWiseExpenses.Food").value(500.0));
    }

    //To get monthly expense summary
    @Test
    void shouldReturnMonthlySummary() throws Exception {

        ExpenseSummaryResponse response =
                ExpenseSummaryResponse.builder()
                        .totalExpenses(1200.0)
                        .categoryWiseExpenses(Map.of("Food", 1200.0))
                        .build();

        when(expenseService.getMonthlySummary(2026, 8))
                .thenReturn(response);

        mockMvc.perform(get("/api/v1/expenses/summary/2026/8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalExpenses").value(1200.0));
    }

    //To delete an expense
    @Test
    void shouldDeleteExpenseSuccessfully() throws Exception {

        doNothing().when(expenseService).deleteExpense(1L);

        mockMvc.perform(delete("/api/v1/expenses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("Resource deleted successfully"));
    }
}
