package dev.shoxruhjon.instagram_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception e, WebRequest request){
        ErrorDetails error = new ErrorDetails(e.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
    }
}
