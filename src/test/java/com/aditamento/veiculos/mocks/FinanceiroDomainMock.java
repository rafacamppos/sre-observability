package com.aditamento.veiculos.mocks;

import com.aditamento.veiculos.adapter.datastore.entity.Financeiro;
import com.aditamento.veiculos.domain.entity.FinanceiroDomain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FinanceiroDomainMock {

    public static FinanceiroDomain getFinanceiroDomain(){
        return FinanceiroDomain.builder()
                .dataCalculo(LocalDate.now())
                .diaPagamento(25)
                .quantidadeParcelas(10)
                .valorTotal(new BigDecimal(5000).setScale(2))
                .valorParcelas(new BigDecimal(500).setScale(2))
                .percentualTaxaJuro(1.2)
                .tipoCalculo("CALCULO_SIMPLES").build();

    }

    public static Financeiro getFinanceiro(){
        return Financeiro.builder()
                .dataCalculo(LocalDate.now())
                .diaPagamento(25)
                .quantidadeParcelas(10)
                .valorTotal(new BigDecimal(5000).setScale(2))
                .valorParcelas(new BigDecimal(500).setScale(2))
                .percentualTaxaJuro(1.2)
                .tipoCalculo("CALCULO_SIMPLES").build();

    }
}
