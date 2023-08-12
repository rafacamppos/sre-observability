package com.aditamento.veiculos.adapter.beans.feign;

import com.aditamento.veiculos.domain.exceptions.ConsultaJurosBadRequestException;
import com.aditamento.veiculos.domain.exceptions.ConsultaJurosGenericErrorException;
import com.aditamento.veiculos.domain.exceptions.ConsultaJurosNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsultaTaxaJurosErrorDecoder implements ErrorDecoder {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()){
            case 400:
                logger.error("Erro ao consultar API de Juros");
                return new ConsultaJurosBadRequestException("Erro ao consultar API de Juros");
            case 404:
                logger.error("Erro ao consultar API de Juros");
                return new ConsultaJurosNotFoundException("Não foi possível encontrar a API de juros");
            default:
                logger.error("Erro genérico ao consultar a API de Juros");
                return new ConsultaJurosGenericErrorException("Erro genérico ao consultar a API de Juros");
        }
    }
}
