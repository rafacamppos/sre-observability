package com.aditamento.veiculos.domain.exceptions;

public class ConsultaJurosNotFoundException extends RuntimeException {

    public ConsultaJurosNotFoundException() {
        super();
    }
    public ConsultaJurosNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public ConsultaJurosNotFoundException(String message) {
        super(message);
    }
    public ConsultaJurosNotFoundException(Throwable cause) {
        super(cause);
    }
}
