package com.aditamento.veiculos.adapter.controller.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;

@Getter
@SuperBuilder
public class ContratoDTO {

    private Integer idContrato;
    private String numeroCpfCnpjCliente;
    private LocalDate dataContratacao;
    private Boolean ativo;
    private Boolean parcelasEmAtraso;

}
