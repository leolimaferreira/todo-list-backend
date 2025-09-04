package com.todolist.dtos.task;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record SaveTaskDTO(
        @NotBlank(message = "Title is required")
        @Size(min = 3, max = 50, message = "Title must be between 3 and 255 characters")
        String title,
        @NotBlank(message = "Description is required")
        @Size(min = 3, max = 255, message = "Description must be between 3 and 255 characters")
        String description,
        @FutureOrPresent(message = "Due date must be today or in the future")
        LocalDate dueDate
) {
}
