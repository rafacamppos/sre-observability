package com.aditamento.veiculos.aplication.usecases;

import com.aditamento.veiculos.adapter.datastore.entity.Aditamento;
import com.aditamento.veiculos.domain.entity.AlteraNumeroParcelas;
import org.springframework.stereotype.Component;

@Component
public interface AlteraNumeroParcelaUseCase {

    Aditamento alteraNumeroParcelas(AlteraNumeroParcelas alteraNumeroParcelas);
}
