package com.aditamento.veiculos.adapter.controller.mapper;

import com.aditamento.veiculos.adapter.controller.entity.AditamentoQuantidadeParcelasEntity;
import com.aditamento.veiculos.adapter.controller.entity.ContratoEntity;
import com.aditamento.veiculos.adapter.controller.entity.FinanceiroEntity;
import com.aditamento.veiculos.adapter.controller.entity.contratos.AlteracaoNumeroParcelas;
import com.aditamento.veiculos.domain.entity.AlteraNumeroParcelas;
import com.aditamento.veiculos.domain.entity.FinanceiroDomain;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlteracaoNumeroParcelasToAlteraNumeroParcelaMapperImpl implements EntityMap<AlteracaoNumeroParcelas, AlteraNumeroParcelas> {

    @Override
    public AlteraNumeroParcelas map(AlteracaoNumeroParcelas alteracaoNumeroParcelas) {
        ContratoEntity contrato = alteracaoNumeroParcelas.getContrato();
        List<FinanceiroEntity> listaFinanceiro = alteracaoNumeroParcelas.getFinanceiro();
        AditamentoQuantidadeParcelasEntity aditamentoEntity = alteracaoNumeroParcelas.getAditamento();

        return AlteraNumeroParcelas.builder()
                .ativo(contrato.getAtivo())
                .dataContratacao(contrato.getDataContratacao())
                .idContrato(contrato.getIdContrato())
                .novaQuantidadeParcelas(aditamentoEntity.getNovaQuantidadeParcelas())
                .parcelasEmAtraso(contrato.getParcelasEmAtraso())
                .numeroCpfCnpjCliente(contrato.getNumeroCpfCnpjCliente())
                .financeiro(this.getFinanceiro(listaFinanceiro))
                .build();
    }

    private List<FinanceiroDomain> getFinanceiro(List<FinanceiroEntity> financeiro){
         return financeiro.stream().map(fin->
                 FinanceiroDomain.builder()
                         .dataCalculo(fin.getDataCalculo())
                .diaPagamento(fin.getDiaPagamento())
                .percentualTaxaJuro(fin.getPercentualTaxaJuro())
                .quantidadeParcelas(fin.getQuantidadeParcelas())
                .tipoCalculo(fin.getTipoCalculo())
                .valorParcelas(fin.getValorParcelas())
                .valorTotal(fin.getValorTotal()).build()).collect(Collectors.toList());
    }
}
