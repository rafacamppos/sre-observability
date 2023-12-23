package com.aditamento.veiculos.adapter.datastore.entity;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class Contrato {

    private Integer idContrato;
    private Integer ultimoDigitoContrato;
    private String numeroCpfCnpjCliente;
    private LocalDate dataContratacao;
    private boolean ativo;
    private boolean parcelasEmAtraso;
}
