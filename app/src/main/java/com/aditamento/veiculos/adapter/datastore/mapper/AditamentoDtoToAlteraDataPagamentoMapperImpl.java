package com.aditamento.veiculos.adapter.datastore.mapper;

import com.aditamento.veiculos.adapter.controller.dto.AditamentoDTO;
import com.aditamento.veiculos.adapter.controller.dto.ContratoDTO;
import com.aditamento.veiculos.adapter.controller.dto.FinanceiroDTO;
import com.aditamento.veiculos.domain.entity.AlteraDataPagamento;
import com.aditamento.veiculos.domain.entity.FinanceiroDomain;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AditamentoDtoToAlteraDataPagamentoMapperImpl implements Mapper<AditamentoDTO, AlteraDataPagamento> {

    @Override
    public AlteraDataPagamento map(AditamentoDTO aditamentoDTO) {
        ContratoDTO contratoDTO = aditamentoDTO.getContratoDTO();
        List<FinanceiroDTO> financeiroDTO = aditamentoDTO.getFinanceiroDTO();
        return AlteraDataPagamento.builder()
                .novaDataPagamento(aditamentoDTO.getNovaDataPagamento())
                .idContrato(contratoDTO.getIdContrato())
                .numeroCpfCnpjCliente(contratoDTO.getNumeroCpfCnpjCliente())
                .dataContratacao(contratoDTO.getDataContratacao())
                .ativo(contratoDTO.getAtivo())
                .parcelasEmAtraso(contratoDTO.getParcelasEmAtraso())
                .financeiro(this.getFinanceiro(financeiroDTO)).build();

    }

    private List<FinanceiroDomain> getFinanceiro(List<FinanceiroDTO> financeiro){
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
