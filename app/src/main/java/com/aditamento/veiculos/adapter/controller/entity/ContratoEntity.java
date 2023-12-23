package com.aditamento.veiculos.adapter.controller.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ContratoEntity {

    @NotNull(message = "o campo id_contrato é obrigatório")
    private Integer idContrato;

    @NotNull(message = "O campo numero_cpf_cnpj_cliente é obrigatório")
    private String numeroCpfCnpjCliente;

    @NotNull(message = "O campo data_contratacao é obrigatório")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataContratacao;

    @NotNull(message = "O campo ativo é obrigatório")
    private Boolean ativo;

    @NotNull(message = "O campo parcelas_em_atraso é obrigatório")
    private Boolean parcelasEmAtraso;
}
