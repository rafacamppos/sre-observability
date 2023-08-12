package com.aditamento.veiculos.domain.entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@SuperBuilder
public class FinanceiroDomain {

    private LocalDate dataCalculo;
    private String tipoCalculo;
    private BigDecimal valorTotal;
    private Integer quantidadeParcelas;
    private BigDecimal valorParcelas;
    private Integer diaPagamento;
    private Double percentualTaxaJuro;
}
