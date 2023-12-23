package com.aditamento.veiculos.adapter.datastore.entity;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class JurosCalculado {
    private BigDecimal valorTotal;
    private Double percentualJuros;
}
