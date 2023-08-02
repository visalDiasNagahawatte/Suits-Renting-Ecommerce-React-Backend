package com.example.ecommerceapi.adviser;

import com.example.ecommerceapi.utill.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 21-Jul-23
 */
@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler({ MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) ->{
            errors.put(
                    ((FieldError) error).getField(),
                    error.getDefaultMessage()
            );
        });
        return new ResponseEntity<>(
                new StandardResponse(400, "Validation Exception", errors),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StandardResponse> handleResponseStatusException(ResponseStatusException ex){
        return new ResponseEntity<>(
                new StandardResponse(401, "Status Error", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandardResponse> handleNullPointException(NullPointerException ex){
        ex.printStackTrace();
        return new ResponseEntity<>(
                new StandardResponse(404, "Null Point Error", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<StandardResponse> handleSqlException(SQLException ex){
        return new ResponseEntity<>(
                new StandardResponse(404, "SQL Error", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse> handleGlobalException(Exception ex){
        ex.printStackTrace();
        return new ResponseEntity<>(
                new StandardResponse(500, "Server Error", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
