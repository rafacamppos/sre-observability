package com.aditamento.veiculos.adapter.datastore.mapper;

import com.aditamento.veiculos.adapter.datastore.entity.Aditamento;
import com.aditamento.veiculos.adapter.datastore.entity.Contrato;
import com.aditamento.veiculos.adapter.datastore.entity.Financeiro;
import com.aditamento.veiculos.domain.entity.AlteraDataPagamento;
import com.aditamento.veiculos.domain.entity.FinanceiroDomain;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlteraDataPagamentoToAditamentoMapperImpl implements Mapper<AlteraDataPagamento, Aditamento> {

    @Override
    public Aditamento map(AlteraDataPagamento alteraDiaPagamento) {
        String strUltimoDigito = String.valueOf(alteraDiaPagamento.getIdContrato()).substring(String.valueOf(alteraDiaPagamento.getIdContrato()).length()-1);
        return Aditamento.builder()
                .contrato(Contrato.builder()
                        .ativo(alteraDiaPagamento.isAtivo())
                        .dataContratacao(alteraDiaPagamento.getDataContratacao())
                        .idContrato(alteraDiaPagamento.getIdContrato())
                        .numeroCpfCnpjCliente(alteraDiaPagamento.getNumeroCpfCnpjCliente())
                        .ultimoDigitoContrato(Integer.valueOf(strUltimoDigito))
                        .parcelasEmAtraso(alteraDiaPagamento.isParcelasEmAtraso()).build())
                .financeiro(this.getFinanceiro(alteraDiaPagamento.getFinanceiro())).build();
    }

    private List<Financeiro> getFinanceiro(List<FinanceiroDomain> financeiro){
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
