package com.stroymaster.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        // Логирование исключения
        log.error("An exception occurred: {}", ex.getMessage(), ex);

        // Создание объекта с сообщением об ошибке
        ErrorMessage errorMessage = new ErrorMessage("An error occurred. Please try again later.");

        // Возвращение ResponseEntity с кодом состояния 500 (Внутренняя ошибка сервера) и объектом ошибки
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String errorMessage = bindingResult.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }


}
