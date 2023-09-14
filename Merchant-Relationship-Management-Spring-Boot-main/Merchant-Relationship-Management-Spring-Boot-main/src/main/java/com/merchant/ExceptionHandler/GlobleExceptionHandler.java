package com.merchant.ExceptionHandler;


import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handlerNotValidException(MethodArgumentNotValidException ex){
            Map<String,String> resp=new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error)->{
                //Type Cast error into FieldError
              String fieldName=  ((FieldError)error).getField();
              String messege=error.getDefaultMessage();

              resp.put(fieldName,messege);
            });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }
}
