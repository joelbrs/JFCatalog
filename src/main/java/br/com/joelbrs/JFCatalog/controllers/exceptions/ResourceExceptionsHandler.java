package br.com.joelbrs.JFCatalog.controllers.exceptions;

import br.com.joelbrs.JFCatalog.services.exceptions.DatabaseException;
import br.com.joelbrs.JFCatalog.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionsHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource Not Found!";
        HttpStatus status = HttpStatus.NOT_FOUND;
        String path = request.getRequestURI();

        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), e.getMessage(), error, path));
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> dataBaseException(DatabaseException e, HttpServletRequest request) {
        String error = "DataBase Exception!";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();

        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), e.getMessage(), error, path));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        var err = new ValidationError();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        err.setError("Validation Exception!");
        err.setStatus(status.value());
        err.setPath(request.getRequestURI());
        err.setTimestamp(Instant.now());

        for (FieldError field : e.getBindingResult().getFieldErrors()) {
            err.addError(new FieldMessage(field.getField(), field.getDefaultMessage()));
        }

        return ResponseEntity.status(status).body(err);
    }
}











