package com.aditamento.veiculos.adapter.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinanceiroResponse {

    private LocalDate dataCalculo;
    private String tipoCalculo;
    private BigDecimal valorTotal;
    private Integer quantidadeParcelas;
    private BigDecimal valorParcelas;
    private Integer diaPagamento;
    private Double percentualTaxaJuro;
}
