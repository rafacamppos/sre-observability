package com.aditamento.veiculos.adapter.datastore.mapper;

import com.aditamento.veiculos.adapter.datastore.entity.Aditamento;
import com.aditamento.veiculos.adapter.datastore.entity.Contrato;
import com.aditamento.veiculos.adapter.datastore.entity.Financeiro;
import com.aditamento.veiculos.domain.entity.AlteraDataPagamento;
import com.aditamento.veiculos.domain.entity.AlteraNumeroParcelas;
import com.aditamento.veiculos.domain.entity.FinanceiroDomain;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlteraNumeroParcelasToAditamentoMapperImpl implements Mapper<AlteraNumeroParcelas, Aditamento> {

    @Override
    public Aditamento map(AlteraNumeroParcelas alteraNumeroParcelas) {
        String strUltimoDigito = String.valueOf(alteraNumeroParcelas.getIdContrato()).substring(String.valueOf(alteraNumeroParcelas.getIdContrato()).length()-1);
        return Aditamento.builder()
                .contrato(Contrato.builder()
                        .ativo(alteraNumeroParcelas.isAtivo())
                        .dataContratacao(alteraNumeroParcelas.getDataContratacao())
                        .idContrato(alteraNumeroParcelas.getIdContrato())
                        .numeroCpfCnpjCliente(alteraNumeroParcelas.getNumeroCpfCnpjCliente())
                        .ultimoDigitoContrato(Integer.valueOf(strUltimoDigito))
                        .parcelasEmAtraso(alteraNumeroParcelas.isParcelasEmAtraso()).build())
                .financeiro(this.getFinanceiro(alteraNumeroParcelas.getFinanceiro())).build();
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
