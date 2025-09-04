package com.todolist.dtos.error;

public record FieldErrorDTO(
        String field,
        String error
) {
}
