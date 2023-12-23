package com.aditamento.veiculos.domain.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
public class AlteraNumeroParcelas extends ContratoDomain {
    private Integer novaQuantidadeParcelas;
}
