package com.dkarachurin.trainTickets.web;

import com.dkarachurin.trainTickets.util.exceptions.NotFoundException;
import com.dkarachurin.trainTickets.util.exceptions.ReservationException;
import com.dkarachurin.trainTickets.util.exceptions.TicketTransactionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.OptimisticLockException;

/**
 * Created by Denis Karachurin on 03.09.2017.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { TicketTransactionException.class, NotFoundException.class, ReservationException.class, OptimisticLockException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
