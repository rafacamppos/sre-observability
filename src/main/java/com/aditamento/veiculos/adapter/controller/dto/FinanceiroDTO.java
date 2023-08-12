package com.aditamento.veiculos.adapter.controller.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;


import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@SuperBuilder
public class FinanceiroDTO {

    private LocalDate dataCalculo;
    private String tipoCalculo;
    private BigDecimal valorTotal;
    private Integer quantidadeParcelas;
    private BigDecimal valorParcelas;
    private Integer diaPagamento;
    private Double percentualTaxaJuro;
}
