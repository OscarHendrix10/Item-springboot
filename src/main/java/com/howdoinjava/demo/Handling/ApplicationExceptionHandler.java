package com.howdoinjava.demo.Handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ApplicationExceptionHandler {
  
  @ExceptionHandler(ItemNotFoundException.class) //se ejecuta cuando se lanza la excepcion ItemNotFoundException
  @ResponseBody //devuelve el mensaje de error
  @ResponseStatus(HttpStatus.NOT_FOUND) //devuelve el codigo de error 404
  
  String itemNotFoundHandler(ItemNotFoundException ex) {
    return ex.getMessage(); //devuelve el mensaje de error
  }
}
