package com.aditamento.veiculos.adapter.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContratoResponse {

    private Integer idContrato;
    private Integer ultimoDigitoContrato;
    private String numeroCpfCnpjCliente;
    private LocalDate dataContratacao;
    private boolean ativo;
    private boolean parcelasEmAtraso;
}
