package com.aditamento.veiculos.adapter.datastore.entity;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ConsultaTaxaJuros {
    private ContratoConsultaTaxaJuros contrato;
}
