package com.aditamento.veiculos.adapter.datastore.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ContratoConsultaTaxaJuros {
    private LocalDate definirDataContratacao;
    private String definirCriterioCalculo;
    private Integer definirQuantidadeParcelas;
    private BigDecimal definirValorContratacao;
}
