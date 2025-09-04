package com.todolist.handler;

import com.todolist.dtos.error.FieldErrorDTO;
import com.todolist.dtos.error.ResponseErrorDTO;
import com.todolist.exception.InvalidFieldException;
import com.todolist.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseErrorDTO handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        String path = request.getRequestURI();
        return ResponseErrorDTO.of(HttpStatus.NOT_FOUND, e.getMessage(), List.of(), path);
    }

    @ExceptionHandler(InvalidFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDTO handleInvalidFieldException(InvalidFieldException e, HttpServletRequest request) {
        String path = request.getRequestURI();
        return ResponseErrorDTO.of(
                HttpStatus.BAD_REQUEST,
                "Validation error",
                List.of(new FieldErrorDTO(e.getField(), e.getMessage())),
                path
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<FieldErrorDTO> fieldErrorsList = fieldErrors.stream()
                .map(error -> new FieldErrorDTO(error.getField(), error.getDefaultMessage()))
                .toList();
        String path = request.getRequestURI();
        return ResponseErrorDTO.unprocessableEntity("Validation error", fieldErrorsList, path);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseErrorDTO handleGenericException(Exception e, HttpServletRequest request) {
        String path = request.getRequestURI();
        return ResponseErrorDTO.of(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred",
                List.of(),
                path
        );
    }

}
