package com.aditamento.veiculos.aplication.usecases;

import com.aditamento.veiculos.adapter.datastore.entity.Aditamento;
import com.aditamento.veiculos.adapter.datastore.entity.ConsultaTaxaJuros;
import com.aditamento.veiculos.adapter.datastore.entity.JurosCalculado;
import com.aditamento.veiculos.adapter.datastore.mapper.Mapper;
import com.aditamento.veiculos.aplication.services.ConsultaTaxaJurosService;
import com.aditamento.veiculos.domain.entity.AlteraNumeroParcelas;
import com.aditamento.veiculos.domain.entity.FinanceiroDomain;
import com.aditamento.veiculos.domain.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AlteraNumeroParcelaUseCaseImpl implements AlteraNumeroParcelaUseCase{

    private final ConsultaTaxaJurosService consultaTaxaJurosService;
    private final Mapper<AlteraNumeroParcelas, ConsultaTaxaJuros> alteraParcelasToConsultaTaxaMapper;
    private final Mapper<AlteraNumeroParcelas, Aditamento> alteraNumeroParcelasAditamentoMapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Aditamento alteraNumeroParcelas(AlteraNumeroParcelas alteraNumeroParcelas) {
        logger.debug("Iniciando alteração do numero de parcelas {}", alteraNumeroParcelas);
        this.validaContrato(alteraNumeroParcelas, alteraNumeroParcelas.getFinanceiro().get(alteraNumeroParcelas.getFinanceiro().size()-1).getQuantidadeParcelas());
        List<FinanceiroDomain> listaFinanceiro = new ArrayList<>(alteraNumeroParcelas.getFinanceiro());
        JurosCalculado jurosCalculado = consultaTaxaJurosService.consultaTaxaJuros(alteraParcelasToConsultaTaxaMapper.map(alteraNumeroParcelas));
        listaFinanceiro.add(this.getFinanceiro(alteraNumeroParcelas, jurosCalculado));
        alteraNumeroParcelas.setFinanceiro(listaFinanceiro);
        logger.info("Número de parcelas alterado com sucesso {}", alteraNumeroParcelas);
        return alteraNumeroParcelasAditamentoMapper.map(alteraNumeroParcelas);
    }

    private FinanceiroDomain getFinanceiro(AlteraNumeroParcelas alteraNumeroParcelas, JurosCalculado jurosCalculado){
        FinanceiroDomain ultimoFinanceiro = alteraNumeroParcelas.getFinanceiro().get(alteraNumeroParcelas.getFinanceiro().size()-1);
        return FinanceiroDomain.builder()
                .dataCalculo(LocalDate.now())
                .diaPagamento(ultimoFinanceiro.getDiaPagamento())
                .tipoCalculo(ultimoFinanceiro.getTipoCalculo())
                .percentualTaxaJuro(jurosCalculado.getPercentualJuros())
                .quantidadeParcelas(ultimoFinanceiro.getQuantidadeParcelas())
                .valorParcelas(ultimoFinanceiro.getValorParcelas())
                .valorTotal(jurosCalculado.getValorTotal()).build();
    }
    private void validaContrato(AlteraNumeroParcelas alteraNumeroParcelas, Integer quantidadeParcelasAtual){
        if(!alteraNumeroParcelas.isAtivo()){
            logger.error("Não é possivel alterar data de pagamento, contrato inativo.");
            throw new BusinessException("Não é possivel alterar data de pagamento, contrato inativo.");
        }else if(alteraNumeroParcelas.getNovaQuantidadeParcelas() > quantidadeParcelasAtual){
            logger.error("Não é possivel alterar a quantidade de parcelas, " +
                    "nova quantidade de parcelas não pode ser maior que " + quantidadeParcelasAtual);
            throw new BusinessException("Não é possivel alterar a quantidade de parcelas, " +
                    "nova quantidade de parcelas não pode ser maior que " + quantidadeParcelasAtual);
        }
    }
}
