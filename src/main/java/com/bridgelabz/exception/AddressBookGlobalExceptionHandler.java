package com.bridgelabz.exception;

import com.bridgelabz.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class AddressBookGlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<Response>> webExchangeBindExceptionHandler(Exception exception){
        String errorMessage = "";
        if (exception instanceof WebExchangeBindException) {
            errorMessage = ((WebExchangeBindException) exception).getAllErrors().get(0).getDefaultMessage();
        }
        Response response = new Response(errorMessage, null);
        return Mono.just(new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST));
    }

//    @ExceptionHandler(RuntimeException.class)
//    public Mono<ResponseEntity<Response>> runtimeExceptionHandler(){
//        Response response = new Response("Something went wrong..!!", null);
//        return Mono.just(new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR));
//    }

    @ExceptionHandler(AddressBookException.class)
    public Mono<ResponseEntity<Response>> addressBookExceptionHandler(RuntimeException exception) {
        Response response = new Response(404, exception.getMessage(), null);
        return Mono.just(new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(AddressBookNotFoundException.class)
    public Mono<ResponseEntity<Response>> addressBookNotFoundExceptionHandler(RuntimeException exception) {
        Response response = new Response(404, exception.getMessage(), null);
        return Mono.just(new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND));
    }
}
