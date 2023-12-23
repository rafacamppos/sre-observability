package com.aditamento.veiculos.adapter.datastore.entity;



import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
public class Aditamento {

    private Contrato contrato;
    private List<Financeiro> financeiro;
}
