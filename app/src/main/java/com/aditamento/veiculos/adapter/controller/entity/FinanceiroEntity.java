package com.aditamento.veiculos.adapter.controller.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FinanceiroEntity {

    @NotNull(message = "O campo data_contratacao do financeiro é obrigatório")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCalculo;

    @NotEmpty(message = "O campo tipo_calculo do financeiro não pode ser vazio.")
    @NotNull(message = "O campo tipo_calculo do financeiro é obrigatório")
    private String tipoCalculo;

    @NotNull(message = "O campo valor_total do financeiro é obrigatório")
    private BigDecimal valorTotal;

    @NotNull(message = "O campo quantidade_parcelas do financeiro é obrigatório")
    private Integer quantidadeParcelas;

    @NotNull(message = "O campo valor_parcelas do financeiro é obrigatório")
    private BigDecimal valorParcelas;

    @NotNull(message = "O campo dia_pagamento do financeiro é obrigatório")
    private Integer diaPagamento;

    @NotNull(message = "O campo percentual_taxa_juro do financeiro é obrigatório")
    private Double percentualTaxaJuro;
}
