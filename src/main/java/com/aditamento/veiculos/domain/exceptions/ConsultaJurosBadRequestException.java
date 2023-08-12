package com.aditamento.veiculos.domain.exceptions;

public class ConsultaJurosBadRequestException extends RuntimeException {

    public ConsultaJurosBadRequestException() {
        super();
    }
    public ConsultaJurosBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
    public ConsultaJurosBadRequestException(String message) {
        super(message);
    }
    public ConsultaJurosBadRequestException(Throwable cause) {
        super(cause);
    }
}
