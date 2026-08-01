package com.sooumik.ledgerly.advice;

import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public @Nullable Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ApiResponse<?>) {
            return body;
        }

        if (body instanceof List<?> list && list.isEmpty()) {

            return ApiResponse.builder()
                    .data(list)
                    .message("No expenses found")
                    .build();
        }

        String message = getSuccessMessage(request.getMethod().name(),
                                            request.getURI().getPath());

        return ApiResponse.builder()
                .data(body)
                .message(message)
                .build();
    }

    private String getSuccessMessage(String httpMethod, String path) {

        if ("GET".equals(httpMethod) && path.matches(".*/summary/\\d{4}/\\d{1,2}$")) {

            String[] parts = path.split("/");

            int year = Integer.parseInt(parts[5]);
            int month = Integer.parseInt(parts[6]);

            String monthName = Month.of(month)
                    .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

            return "Expense summary retrieved successfully for "
                    + monthName + " " + year;
        }

        return switch (httpMethod) {
            case "POST" -> "Expense created successfully";
            case "GET" -> "Request processed successfully";
            case "PUT" -> "Resource updated successfully";
            case "DELETE" -> "Resource deleted successfully";
            default -> "Success";
        };
    }
}
