package com.aditamento.veiculos.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
public class ContratoDomain {
    private Integer idContrato;
    private String numeroCpfCnpjCliente;
    private LocalDate dataContratacao;
    private boolean ativo;
    private boolean parcelasEmAtraso;
    private List<FinanceiroDomain> financeiro;
}
