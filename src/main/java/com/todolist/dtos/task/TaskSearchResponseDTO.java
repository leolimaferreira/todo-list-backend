package com.todolist.dtos.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TaskSearchResponseDTO(
        UUID id,
        String title,
        String description,
        boolean completed,
        LocalDate dueDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
