package com.aditamento.veiculos.adapter.controller.server;

import com.aditamento.veiculos.adapter.controller.entity.ErrorMessage;
import com.aditamento.veiculos.domain.exceptions.BusinessException;
import com.aditamento.veiculos.domain.exceptions.ConsultaJurosBadRequestException;
import com.aditamento.veiculos.domain.exceptions.ConsultaJurosNotFoundException;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@ControllerAdvice
@RequiredArgsConstructor
public class ErrorHandlerController{

    private final MeterRegistry registry;
    private AtomicInteger testGauge;

    private Counter errorBusiness;
    private Counter errorConsultaJuros;

    @PostConstruct
    private void init() {
        errorBusiness = registry.counter("erro_negocio","excecao", "BusinessException");
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(BusinessException e, ServletWebRequest webRequest) {
        errorBusiness.increment();
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
