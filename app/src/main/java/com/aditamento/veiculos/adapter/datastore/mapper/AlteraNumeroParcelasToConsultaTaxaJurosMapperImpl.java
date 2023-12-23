package com.aditamento.veiculos.adapter.datastore.mapper;

import com.aditamento.veiculos.adapter.datastore.entity.ConsultaTaxaJuros;
import com.aditamento.veiculos.adapter.datastore.entity.ContratoConsultaTaxaJuros;
import com.aditamento.veiculos.domain.entity.AlteraNumeroParcelas;
import com.aditamento.veiculos.domain.entity.FinanceiroDomain;
import org.springframework.stereotype.Component;

@Component
public class AlteraNumeroParcelasToConsultaTaxaJurosMapperImpl implements Mapper<AlteraNumeroParcelas, ConsultaTaxaJuros>{
    private static final String JUROS_SIMPLES = "JUROS_SIMPLES";

    @Override
    public ConsultaTaxaJuros map(AlteraNumeroParcelas alteraNumeroParcelas) {
        FinanceiroDomain financeiro = alteraNumeroParcelas.getFinanceiro().get(alteraNumeroParcelas.getFinanceiro().size()-1);
        return ConsultaTaxaJuros.builder()
                .contrato(ContratoConsultaTaxaJuros.builder()
                        .definirQuantidadeParcelas(alteraNumeroParcelas.getNovaQuantidadeParcelas())
                        .definirValorContratacao(financeiro.getValorTotal())
                        .definirDataContratacao(alteraNumeroParcelas.getDataContratacao())
                        .definirCriterioCalculo(JUROS_SIMPLES)
                        .build()).build();
    }
}
