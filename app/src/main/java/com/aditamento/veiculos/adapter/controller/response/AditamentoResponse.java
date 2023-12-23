package com.aditamento.veiculos.adapter.controller.response;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AditamentoResponse {

    private ContratoResponse contrato;
    private List<FinanceiroResponse> financeiro;
}
