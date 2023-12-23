package com.aditamento.veiculos.mocks;

import com.aditamento.veiculos.adapter.datastore.entity.Aditamento;
import com.aditamento.veiculos.adapter.datastore.entity.Contrato;
import com.aditamento.veiculos.adapter.datastore.entity.Financeiro;
import com.aditamento.veiculos.domain.entity.AlteraDataPagamento;
import com.aditamento.veiculos.domain.entity.FinanceiroDomain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlteraDataPagamentoMock {

    public static AlteraDataPagamento getAlteraDataPagamento(){
        FinanceiroDomain financeiro = FinanceiroDomainMock.getFinanceiroDomain();
        List<FinanceiroDomain> listaFinanceiro = new ArrayList<>();
        listaFinanceiro.add(financeiro);
        return AlteraDataPagamento.builder()
                .novaDataPagamento(27)
                .parcelasEmAtraso(false)
                .dataContratacao(LocalDate.parse("2023-07-29"))
                .numeroCpfCnpjCliente("35695814296")
                .ativo(true)
                .idContrato(123456789)
                .financeiro(listaFinanceiro)
                .build();
    }

    public static Aditamento getAditamentoAlteradaDataPagamento(){
        FinanceiroDomain financeiro = FinanceiroDomainMock.getFinanceiroDomain();
        List<FinanceiroDomain> listaFinanceiro = new ArrayList<>();
        listaFinanceiro.add(financeiro);
        return Aditamento.builder()
                .contrato(Contrato.builder()
                        .ativo(true)
                        .dataContratacao(LocalDate.parse("2023-07-29"))
                        .idContrato(123456789)
                        .numeroCpfCnpjCliente("35695814296")
                        .ultimoDigitoContrato(9)
                        .parcelasEmAtraso(false).build())
                .financeiro(getFinanceiro(listaFinanceiro)).build();



    }

    private static List<Financeiro> getFinanceiro(List<FinanceiroDomain> financeiro){
        return financeiro.stream().map(fin->
                Financeiro.builder()
                        .dataCalculo(fin.getDataCalculo())
                        .diaPagamento(fin.getDiaPagamento())
                        .percentualTaxaJuro(fin.getPercentualTaxaJuro())
                        .quantidadeParcelas(fin.getQuantidadeParcelas())
                        .tipoCalculo(fin.getTipoCalculo())
                        .valorParcelas(fin.getValorParcelas())
                        .valorTotal(fin.getValorTotal()).build()).collect(Collectors.toList());
    }


}
