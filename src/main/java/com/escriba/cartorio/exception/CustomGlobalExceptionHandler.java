package com.escriba.cartorio.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        //Get all errors
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getField() + ": " + x.getDefaultMessage()).collect(Collectors.toList());

        Map<String, Object> body = createResponseBody(status, errors.get(0).toString());

        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<Object> handleException(BusinessRuleException exception) {

        Map<String, Object> body = createResponseBody(exception.getHttpStatus(), exception.getMessage());
        return new ResponseEntity<>(body, exception.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (!(ex.getCause() instanceof JsonMappingException)) {
            return new ResponseEntity<>("", headers, status);
        }

        JsonMappingException e = (JsonMappingException) ex.getCause();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        List<String> messages = new ArrayList<>();

        for (JsonMappingException.Reference reference : e.getPath()) {
            String fieldName = reference.getFieldName();
            messages.add(fieldName + ": Campo inválido!");
        }

        body.put("messages", messages);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleException(ConstraintViolationException exception) {

        Map<String, Object> body = createResponseBody(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> createResponseBody(HttpStatus status, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("message", message);
        return body;
    }
}