package com.todolist.dtos.task;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateTaskDTO(
        @Size(min = 3, max = 50, message = "Title must be between 3 and 255 characters")
        String title,
        @Size(min = 3, max = 255, message = "Description must be between 3 and 255 characters")
        String description,
        @FutureOrPresent(message = "Due date must be today or in the future")
        LocalDate dueDate
) {
}
