package br.com.joelbrs.JFCatalog.controllers.exceptions;

import br.com.joelbrs.JFCatalog.services.exceptions.DatabaseException;
import br.com.joelbrs.JFCatalog.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        String error = "DataBase Error!";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();

        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), e.getMessage(), error, path));
    }
}











