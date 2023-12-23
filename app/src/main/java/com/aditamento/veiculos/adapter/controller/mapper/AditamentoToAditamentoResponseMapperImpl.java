package com.aditamento.veiculos.adapter.controller.mapper;

import com.aditamento.veiculos.adapter.controller.response.AditamentoResponse;
import com.aditamento.veiculos.adapter.controller.response.ContratoResponse;
import com.aditamento.veiculos.adapter.controller.response.FinanceiroResponse;
import com.aditamento.veiculos.adapter.datastore.entity.Aditamento;
import com.aditamento.veiculos.adapter.datastore.entity.Contrato;
import com.aditamento.veiculos.adapter.datastore.entity.Financeiro;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AditamentoToAditamentoResponseMapperImpl implements EntityMap<Aditamento, AditamentoResponse> {

    @Override
    public AditamentoResponse map(Aditamento aditamento) {
        Contrato contrato = aditamento.getContrato();
        return AditamentoResponse.builder()
                .contrato(ContratoResponse.builder()
                        .idContrato(contrato.getIdContrato())
                        .ativo(contrato.isAtivo())
                        .ultimoDigitoContrato(contrato.getUltimoDigitoContrato())
                        .dataContratacao(contrato.getDataContratacao())
                        .numeroCpfCnpjCliente(contrato.getNumeroCpfCnpjCliente()).build())
                .financeiro(this.getFinanceiro(aditamento.getFinanceiro())).build();
    }

    private List<FinanceiroResponse> getFinanceiro(List<Financeiro> financeiro){
         return financeiro.stream().map(fin->
                 FinanceiroResponse.builder()
                         .dataCalculo(fin.getDataCalculo())
                .diaPagamento(fin.getDiaPagamento())
                .percentualTaxaJuro(fin.getPercentualTaxaJuro())
                .quantidadeParcelas(fin.getQuantidadeParcelas())
                .tipoCalculo(fin.getTipoCalculo())
                .valorParcelas(fin.getValorParcelas())
                .valorTotal(fin.getValorTotal()).build()).collect(Collectors.toList());
    }
}
