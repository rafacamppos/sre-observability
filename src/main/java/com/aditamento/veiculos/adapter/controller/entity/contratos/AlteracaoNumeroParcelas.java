package com.aditamento.veiculos.adapter.controller.entity.contratos;

import com.aditamento.veiculos.adapter.controller.entity.AditamentoQuantidadeParcelasEntity;
import com.aditamento.veiculos.adapter.controller.entity.ContratoEntity;
import com.aditamento.veiculos.adapter.controller.entity.FinanceiroEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AlteracaoNumeroParcelas {

    @Valid
    @NotNull(message = "Dados do contrato são obrigatórios")
    private ContratoEntity contrato;

    @Valid
    @NotNull(message = "Dados do financeiro são obrigatórios")
    private List<FinanceiroEntity> financeiro;

    @Valid
    @NotNull(message = "Dados do aditamento são obrigatórios")
    private AditamentoQuantidadeParcelasEntity aditamento;
}
