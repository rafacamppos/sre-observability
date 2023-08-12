package com.aditamento.veiculos.aplication.services;

import com.aditamento.veiculos.adapter.datastore.entity.ConsultaTaxaJuros;
import com.aditamento.veiculos.adapter.datastore.entity.ContratoConsultaTaxaJuros;
import com.aditamento.veiculos.adapter.datastore.entity.JurosCalculado;
import org.springframework.stereotype.Component;

@Component
public interface ConsultaTaxaJurosService {

    JurosCalculado consultaTaxaJuros(ConsultaTaxaJuros contratoTaxaJuros);
}
