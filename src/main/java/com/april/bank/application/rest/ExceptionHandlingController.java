package com.april.bank.application.rest;

import com.april.bank.domain.exception.NotEnoughMoneyException;
import com.april.bank.domain.service.ObjectNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice(assignableTypes = AccountController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotEnoughMoneyException.class)
    public final ResponseEntity<Object> handleNotEnoughMoneyException(NotEnoughMoneyException exception, WebRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public final ResponseEntity<Object> handleObjectNotFoundException(ObjectNotFoundException exception, WebRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

}
