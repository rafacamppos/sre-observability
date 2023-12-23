package com.aditamento.veiculos.adapter.controller.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class AditamentoDTO {
    private ContratoDTO contratoDTO;
    private List<FinanceiroDTO> financeiroDTO;
    private Integer novaDataPagamento;
    private Integer novaQuantidadeParcelas;
}
