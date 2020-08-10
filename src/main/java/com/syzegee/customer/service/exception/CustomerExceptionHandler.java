 /*
  * To change this license header, choose License Headers in Project Properties.
  * To change this template file, choose Tools | Templates
  * and open the template in the editor.
  */
 package com.syzegee.customer.service.exception;

 import org.springframework.http.HttpHeaders;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.http.converter.HttpMessageNotReadableException;
 import org.springframework.web.bind.annotation.ControllerAdvice;
 import org.springframework.web.bind.annotation.ExceptionHandler;
 import org.springframework.web.bind.annotation.RestController;
 import org.springframework.web.context.request.WebRequest;
 import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

 import java.io.IOException;

 /**
  * @author Sangam
  */
 @ControllerAdvice
 @RestController
 public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {

     @Override
     protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                   HttpHeaders headers, HttpStatus status, WebRequest request) {
         String error = "Malformed JSON request";
         return buildResponseEntity(new ErrorMessage(status, status.value(), error));
     }

     private ResponseEntity<Object> buildResponseEntity(ErrorMessage errorMessage) {
         return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
     }

     @ExceptionHandler(Exception.class)
     public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex, WebRequest request) {
         ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),  "Bad Request Error Occured..!");
         return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(CustomerServiceException.class)
     public final ResponseEntity<ErrorMessage> handleUserNotFoundException(CustomerServiceException exception,
                                                                           WebRequest request) {
         ErrorMessage errorMessage
                 = new ErrorMessage(exception.getHttpStatus(), exception.getCode(),
                 exception.getMessage());
         return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
     }

     @ExceptionHandler(IOException.class)
     public final ResponseEntity<ErrorMessage> handleIOException(IOException ex, WebRequest request) {
         ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(),
                 "exception occured while fetching the record");
         return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
     }
     @ExceptionHandler(CustomerRuntimeException.class)
     public final ResponseEntity<ErrorMessage> handleCustomerRuntimeException(CustomerRuntimeException ex, WebRequest request) {
         ErrorMessage errorMessage = new ErrorMessage(ex.getStatus(), ex.getCode(),ex.getMessage());
         return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
     }
 }
