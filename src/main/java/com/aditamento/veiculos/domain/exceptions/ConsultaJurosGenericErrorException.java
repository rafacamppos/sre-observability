package com.aditamento.veiculos.domain.exceptions;

public class ConsultaJurosGenericErrorException extends RuntimeException {

    public ConsultaJurosGenericErrorException() {
        super();
    }
    public ConsultaJurosGenericErrorException(String message, Throwable cause) {
        super(message, cause);
    }
    public ConsultaJurosGenericErrorException(String message) {
        super(message);
    }
    public ConsultaJurosGenericErrorException(Throwable cause) {
        super(cause);
    }
}
