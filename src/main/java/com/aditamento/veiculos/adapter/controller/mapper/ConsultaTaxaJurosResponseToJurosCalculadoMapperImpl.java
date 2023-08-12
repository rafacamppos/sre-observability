package com.aditamento.veiculos.adapter.controller.mapper;

import com.aditamento.veiculos.adapter.controller.entity.ConsultaTaxaJurosResponse;
import com.aditamento.veiculos.adapter.datastore.entity.JurosCalculado;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ConsultaTaxaJurosResponseToJurosCalculadoMapperImpl implements EntityMap<ResponseEntity<ConsultaTaxaJurosResponse>, JurosCalculado>{

    @Override
    public JurosCalculado map(ResponseEntity<ConsultaTaxaJurosResponse> consultaTaxaJurosResponseResponseEntity) {
        return JurosCalculado.builder()
                .percentualJuros(consultaTaxaJurosResponseResponseEntity.getBody().getData().getPercentualJuros())
                .valorTotal(consultaTaxaJurosResponseResponseEntity.getBody().getData().getValorTotal()).build();
    }
}
