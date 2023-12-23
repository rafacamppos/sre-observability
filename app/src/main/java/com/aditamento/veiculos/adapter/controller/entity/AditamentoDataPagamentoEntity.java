package com.aditamento.veiculos.adapter.controller.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = false)
public class AditamentoDataPagamentoEntity {

    @NotNull(message = "O campo nova_data_pagamento é obrigatório")
    @Min(value = 1, message = "Valor mínimo para o campo nova_data_pagamento é 1")
    @Max(value = 28, message = "Valor máximo para o campo nova_data_pagamento é 28")
    private Integer novaDataPagamento;
}
