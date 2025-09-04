package com.todolist.handler;

import com.todolist.dtos.error.ErrorResponseDTO;
import com.todolist.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        String path = request.getRequestURI();
        return ErrorResponseDTO.of(HttpStatus.NOT_FOUND, e.getMessage(), List.of(), path);
    }
}
