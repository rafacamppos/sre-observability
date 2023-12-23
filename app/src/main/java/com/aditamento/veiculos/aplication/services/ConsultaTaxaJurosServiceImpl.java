package com.aditamento.veiculos.aplication.services;


import com.aditamento.veiculos.adapter.controller.client.ConsultaTaxaJurosClient;
import com.aditamento.veiculos.adapter.controller.dto.JurosCalculadoDTO;
import com.aditamento.veiculos.adapter.controller.entity.ConsultaTaxaJurosResponse;
import com.aditamento.veiculos.adapter.controller.mapper.EntityMap;
import com.aditamento.veiculos.adapter.datastore.entity.ConsultaTaxaJuros;
import com.aditamento.veiculos.adapter.datastore.entity.JurosCalculado;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
@RequiredArgsConstructor
public class ConsultaTaxaJurosServiceImpl implements ConsultaTaxaJurosService {

    private final ConsultaTaxaJurosClient client;
    private final EntityMap<ResponseEntity<ConsultaTaxaJurosResponse>, JurosCalculado> responseEntityJurosCalculadoEntityMap;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public JurosCalculado consultaTaxaJuros(ConsultaTaxaJuros contratoTaxaJuros) {
        logger.debug("Iniciando consulta da taxa de juros {}", contratoTaxaJuros);
        //ResponseEntity<ConsultaTaxaJurosResponse> responseEntity = client.consultaTaxaJuros(contratoTaxaJuros);
        ConsultaTaxaJurosResponse jurosResponse = new ConsultaTaxaJurosResponse();
        jurosResponse.setData(JurosCalculadoDTO.builder().percentualJuros(1.98).valorTotal(new BigDecimal(5000)).build());
        ResponseEntity<ConsultaTaxaJurosResponse> responseEntity = ResponseEntity.ok(jurosResponse);

        logger.info("Taxa de juros consultada com sucesso! {}", responseEntity);
        return responseEntityJurosCalculadoEntityMap.map(responseEntity);
    }
}
