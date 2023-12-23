package com.aditamento.veiculos.adapter.controller.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = false)
public class AditamentoQuantidadeParcelasEntity {
    @NotNull(message = "O campo nova_quantidade_parcelas é obrigatório")
    private Integer novaQuantidadeParcelas;
}
