package com.aditamento.veiculos.aplication.usecases;

import com.aditamento.veiculos.adapter.datastore.entity.Aditamento;
import com.aditamento.veiculos.domain.entity.AlteraDataPagamento;
import org.springframework.stereotype.Component;

@Component
public interface AlteraDataPagamentoUseCase {
    Aditamento alteraDataPagamento(AlteraDataPagamento alteraDiaPagamento);
}
