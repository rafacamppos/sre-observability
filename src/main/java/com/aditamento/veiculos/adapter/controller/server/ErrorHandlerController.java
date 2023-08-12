package com.aditamento.veiculos.adapter.controller.server;

import com.aditamento.veiculos.adapter.controller.entity.ErrorMessage;
import com.aditamento.veiculos.domain.exceptions.BusinessException;
import com.aditamento.veiculos.domain.exceptions.ConsultaJurosBadRequestException;
import com.aditamento.veiculos.domain.exceptions.ConsultaJurosNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;


import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class ErrorHandlerController{

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(BusinessException e, ServletWebRequest webRequest) {
        ErrorMessage.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST.value()).build();
        return new ResponseEntity<>(ErrorMessage.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST.value()).url(webRequest.getContextPath()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConsultaJurosBadRequestException.class)
    protected ResponseEntity<Object> handlerConsultaJurosBadRequestException(ConsultaJurosBadRequestException e, ServletWebRequest webRequest) {
        ErrorMessage.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST.value()).build();
        return new ResponseEntity<>(ErrorMessage.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST.value()).url(webRequest.getRequest().getRequestURI()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConsultaJurosNotFoundException.class)
    protected ResponseEntity<Object> handlerConsultaJurosNotFoundException(ConsultaJurosNotFoundException e, ServletWebRequest webRequest) {
        ErrorMessage.builder().message(e.getMessage()).status(HttpStatus.NOT_FOUND.value()).url(webRequest.getRequest().getRequestURI()).build();
        return new ResponseEntity<>(ErrorMessage.builder().message(e.getMessage()).status(HttpStatus.NOT_FOUND.value()).url(webRequest.getRequest().getRequestURI()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, ServletWebRequest webRequest) {
        List<String> errors = new ArrayList<>();
        e.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
        return new ResponseEntity<>(ErrorMessage.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST.value()).url(webRequest.getRequest().getRequestURI()).build(), HttpStatus.BAD_REQUEST);
    }

}
