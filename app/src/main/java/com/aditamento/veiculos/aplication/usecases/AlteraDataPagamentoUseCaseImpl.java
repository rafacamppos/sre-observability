package com.aditamento.veiculos.aplication.usecases;

import com.aditamento.veiculos.adapter.datastore.entity.Aditamento;
import com.aditamento.veiculos.adapter.datastore.mapper.Mapper;
import com.aditamento.veiculos.domain.entity.AlteraDataPagamento;
import com.aditamento.veiculos.domain.entity.FinanceiroDomain;
import com.aditamento.veiculos.domain.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class AlteraDataPagamentoUseCaseImpl implements AlteraDataPagamentoUseCase{

    private final Mapper<AlteraDataPagamento, Aditamento> alteraDiaPagamentoAditamentoMap;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Aditamento alteraDataPagamento(AlteraDataPagamento alteraDataPagamento) {

        logger.debug("Iniciando alteração da data do pagamento {}", alteraDataPagamento);
        this.validaContrato(alteraDataPagamento);
        this.validaNovaDataPagamento(alteraDataPagamento.getFinanceiro().get(0).getDiaPagamento(), alteraDataPagamento.getNovaDataPagamento());
        FinanceiroDomain financeiro = getFinanceiro(alteraDataPagamento);
        List<FinanceiroDomain> listaFinanceiro = new ArrayList<>(alteraDataPagamento.getFinanceiro());
        listaFinanceiro.add(financeiro);
        alteraDataPagamento.setFinanceiro(listaFinanceiro);
        logger.info("Dia de pagamento alterado com sucesso {}", alteraDataPagamento);
        return alteraDiaPagamentoAditamentoMap.map(alteraDataPagamento);
    }

    private FinanceiroDomain getFinanceiro(AlteraDataPagamento alteraDiaPagamento){
        FinanceiroDomain ultimoFinanceiro = alteraDiaPagamento.getFinanceiro().get(alteraDiaPagamento.getFinanceiro().size()-1);
        return FinanceiroDomain.builder()
                .dataCalculo(LocalDate.now())
                .diaPagamento(alteraDiaPagamento.getNovaDataPagamento())
                .tipoCalculo(ultimoFinanceiro.getTipoCalculo())
                .percentualTaxaJuro(ultimoFinanceiro.getPercentualTaxaJuro())
                .quantidadeParcelas(ultimoFinanceiro.getQuantidadeParcelas())
                .valorParcelas(ultimoFinanceiro.getValorParcelas())
                .valorTotal(ultimoFinanceiro.getValorTotal()).build();
    }

    private void validaContrato(AlteraDataPagamento alteraDiaPagamento){
        if(!alteraDiaPagamento.isAtivo()){
            logger.error("Não é possivel alterar data de pagamento, contrato inativo.");
            throw new BusinessException("Não é possivel alterar data de pagamento, contrato inativo.");
        }else if(alteraDiaPagamento.isParcelasEmAtraso()){
            logger.error("Não é possivel alterar data de pagamento, contrato possui parcelas em atraso.");
            throw new BusinessException("Não é possivel alterar data de pagamento, contrato possui parcelas em atraso.");
        }
    }

    private void validaNovaDataPagamento(Integer data, Integer novaData){
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataAtualPagamento = LocalDate.of(dataAtual.getYear(), dataAtual.getMonth(), data);
        LocalDate novaDataPagamento;
        if(novaData < data){
            novaDataPagamento =  LocalDate.of(dataAtual.getYear(), dataAtual.getMonth().plus(1), novaData);
        }else{
            novaDataPagamento = LocalDate.of(dataAtual.getYear(), dataAtual.getMonth(), novaData);
        }
        if(ChronoUnit.DAYS.between(dataAtualPagamento, novaDataPagamento) > 10){
            logger.error("Não é possivel alterar data de pagamento, limite máximo para alteração é " + dataAtualPagamento.plusDays(10));
            throw new BusinessException("Não é possivel alterar data de pagamento, limite máximo para alteração é " + dataAtualPagamento.plusDays(10));
        }
    }
}
