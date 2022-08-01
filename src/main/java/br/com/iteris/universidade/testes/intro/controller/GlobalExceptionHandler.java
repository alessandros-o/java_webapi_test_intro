package br.com.iteris.universidade.testes.intro.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Setter
    @Getter
    private static class JsonResponse{
        String message;

        public JsonResponse(String message){
            super();
            this.message = message;
        }
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<JsonResponse> handleException(Exception e){
        return new ResponseEntity<>(new JsonResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
