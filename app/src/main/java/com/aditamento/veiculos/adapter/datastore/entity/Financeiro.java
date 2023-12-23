package com.aditamento.veiculos.adapter.datastore.entity;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Builder
public class Financeiro {

    private LocalDate dataCalculo;
    private String tipoCalculo;
    private BigDecimal valorTotal;
    private Integer quantidadeParcelas;
    private BigDecimal valorParcelas;
    private Integer diaPagamento;
    private Double percentualTaxaJuro;
}
