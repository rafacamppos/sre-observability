package com.aditamento.veiculos.domain.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
public class AlteraDataPagamento extends ContratoDomain {
    private Integer novaDataPagamento;
}
