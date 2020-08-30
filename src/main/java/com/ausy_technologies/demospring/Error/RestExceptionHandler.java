package com.ausy_technologies.demospring.Error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice  // Adnotarea @ControllerAdvice vă permite să gestionați excepții în întreaga aplicație, nu doar la un controller individual. Vă puteți gândi la acesta ca la un interceptor al excepțiilor aruncate prin metode adnotate cu @RequestMapping(GET, PUT, POST, DELETE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {




    @ExceptionHandler(Exception.class)  // Definim un handler pentru o excepție generică
    public ResponseEntity<Object> hendleGenericException(Exception e , HttpServletResponse response)
    {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        response.setStatus(httpStatus.value());
        return buildResponseEntity(new ApiError(httpStatus,"Internal Server Error: Unexpected Exception",e));
    }



    @ExceptionHandler(UserNotFoundException.class)  //Definim un handler pentru a prinde exceptia UserNotFoundException (în cazul nostru dacă ID-ul căutat nu se află în baza de date)
    public ResponseEntity<Object> handleEntityNotFound(UserNotFoundException ex,HttpServletResponse response)
    {
        HttpStatus httpStatus =HttpStatus.NOT_FOUND;
        return buildResponseEntity(new ApiError(httpStatus,"Ooops! User Not Found !",ex));
    }



    @Override //  Aici suprascriem metoda care se ocupa de  maparea   corecta a verbelor HTTP .(de exemplu daca  vrem sa facem un POST pe un URL de GET , aceasta metoda va arunca raspunsul customizat de noi , nu cel default!)
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_GATEWAY,"Not supported",ex));
    }



    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) // Metodă care creează răspunsul (bazat pe clasa ApiError) în caz de eroare
    {
        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }


}
