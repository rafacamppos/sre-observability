package com.aditamento.veiculos.mocks;

import com.aditamento.veiculos.domain.entity.AlteraNumeroParcelas;
import com.aditamento.veiculos.domain.entity.FinanceiroDomain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AlteraNumeroParcelasMock {

    public static AlteraNumeroParcelas getAlteraNumeroParcelas(){
        FinanceiroDomain financeiro = FinanceiroDomainMock.getFinanceiroDomain();
        List<FinanceiroDomain> listaFinanceiro = new ArrayList<>((Collection) financeiro);
        return AlteraNumeroParcelas.builder()
                .novaQuantidadeParcelas(24)
                .parcelasEmAtraso(false)
                .dataContratacao(LocalDate.parse("2023-07-29"))
                .numeroCpfCnpjCliente("35695814296")
                .ativo(true)
                .idContrato(123456789)
                .financeiro(listaFinanceiro)
                .build();
    }


}
