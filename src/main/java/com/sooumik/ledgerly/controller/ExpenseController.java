package com.sooumik.ledgerly.controller;

import com.sooumik.ledgerly.advice.ApiResponse;
import com.sooumik.ledgerly.dto.request.CreateExpenseRequest;
import com.sooumik.ledgerly.dto.response.ExpenseResponse;
import com.sooumik.ledgerly.dto.response.ExpenseSummaryResponse;
import com.sooumik.ledgerly.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    //To create an Expense
    @PostMapping
    public ResponseEntity<ExpenseResponse> addExpense(@Valid @RequestBody CreateExpenseRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(expenseService.addExpense(request));
    }

    //To get all the expenses
    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    //To get expense by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ExpenseResponse>> getExpenses(@PathVariable String category) {
        if (category != null) {
            return ResponseEntity.ok(expenseService.getExpensesByCategory(category));
        }

        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    //To get expense by keyword
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<ExpenseResponse>> searchExpenses(@PathVariable String keyword) {
        return ResponseEntity.ok(expenseService.searchExpenses(keyword));
    }

    //To get total expense
    @GetMapping("/summary")
    public ResponseEntity<ExpenseSummaryResponse> getExpenseSummary() {
        return ResponseEntity.ok(expenseService.getExpenseSummary());
    }

    //To get monthly expense summary
    @GetMapping("/summary/{year}/{month}")
    public ResponseEntity<ExpenseSummaryResponse> getMonthlySummary(@PathVariable int year, @PathVariable int month) {
        return ResponseEntity.ok(
                expenseService.getMonthlySummary(year, month)
        );
    }

    //To delete an already existing expense
    @DeleteMapping("/{expenseId}")
    public ResponseEntity<ApiResponse<Void>> deleteExpense(@PathVariable Long expenseId) {
        expenseService.deleteExpense(expenseId);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .message("Expense deleted successfully")
                        .build()
        );
    }
}
