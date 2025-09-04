package com.todolist.dtos.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;


public record ErrorResponseDTO(
        int status,
        String message,
        List<FieldErrorDTO> errors,
        LocalDateTime timestamp,
        String path
) {

    public static ErrorResponseDTO defaultResponse(String message, String path) {
        return new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), message, List.of(), LocalDateTime.now(), path);
    }

    public static ErrorResponseDTO conflict(String message, String path) {
        return new ErrorResponseDTO(HttpStatus.CONFLICT.value(), message, List.of(), LocalDateTime.now(), path);
    }

    public static ErrorResponseDTO unprocessableEntity(String message, List<FieldErrorDTO> errors, String path) {
        return new ErrorResponseDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), message, errors, LocalDateTime.now(), path);
    }

    public static ErrorResponseDTO of(HttpStatus status, String message, List<FieldErrorDTO> errors, String path) {
        return new ErrorResponseDTO(status.value(), message, errors, LocalDateTime.now(), path);
    }
}
