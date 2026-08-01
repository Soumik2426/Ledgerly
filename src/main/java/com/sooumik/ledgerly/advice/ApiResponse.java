package com.sooumik.ledgerly.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    private T data;
    private String message;
    private ApiError error;
}
