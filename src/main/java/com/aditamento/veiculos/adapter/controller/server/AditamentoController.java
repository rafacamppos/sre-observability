package com.aditamento.veiculos.adapter.controller.server;

import com.aditamento.veiculos.adapter.controller.dto.AditamentoDTO;
import com.aditamento.veiculos.adapter.controller.entity.contratos.AlteracaoDataPagamento;
import com.aditamento.veiculos.adapter.controller.entity.contratos.AlteracaoNumeroParcelas;
import com.aditamento.veiculos.adapter.controller.mapper.EntityMap;
import com.aditamento.veiculos.adapter.controller.response.AditamentoResponse;

import com.aditamento.veiculos.adapter.datastore.entity.Aditamento;
import com.aditamento.veiculos.adapter.datastore.mapper.Mapper;
import com.aditamento.veiculos.aplication.usecases.AlteraDataPagamentoUseCase;
import com.aditamento.veiculos.aplication.usecases.AlteraNumeroParcelaUseCase;
import com.aditamento.veiculos.domain.entity.AlteraDataPagamento;
import com.aditamento.veiculos.domain.entity.AlteraNumeroParcelas;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.UUID;


@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/aditamento")
@RequiredArgsConstructor
public class AditamentoController {

    private final EntityMap<AlteracaoDataPagamento, AditamentoDTO> alteracaoDataToAditamentoDTO;
    private final EntityMap<AlteracaoNumeroParcelas, AditamentoDTO> alteracaoNumeroParcelasToAditamento;

    private final EntityMap<Aditamento, AditamentoResponse> aditamentoAditamentoResponseEntityMap;
    private final Mapper<AditamentoDTO, AlteraDataPagamento> aditamentoDtoToAlteraDiaPagamento;
    private final Mapper<AditamentoDTO, AlteraNumeroParcelas> aditamentoToAlteraNumeroParcelas;
    private final AlteraDataPagamentoUseCase alteraDataUseCase;

    private final AlteraNumeroParcelaUseCase alteraNumeroParcelasUseCase;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "/altera-quantidade-parcelas",  consumes = "application/json", produces = "application/json")
    private ResponseEntity<?> postAlteraQuantidadeParcela(@Valid @RequestBody AlteracaoNumeroParcelas alteraParcela,
                                                          @RequestHeader ("itau-pos-venda-teste") UUID headerItauPosVenda){
        logger.info("Header itau-pos-venda-teste {}", headerItauPosVenda);
        logger.info("Requisição para alteração de parcela recebida", alteraParcela);
        AditamentoDTO aditamentoDTO = alteracaoNumeroParcelasToAditamento.map(alteraParcela);
        Aditamento aditamento = alteraNumeroParcelasUseCase.alteraNumeroParcelas(aditamentoToAlteraNumeroParcelas.map(aditamentoDTO));
        return this.getDataResponse(aditamento);
    }

    @PostMapping(value = "/altera-dia-pagamento",  consumes = "application/json", produces = "application/json")
    private ResponseEntity<AditamentoResponse> postAlteraDiaPagamento(@Valid @RequestBody AlteracaoDataPagamento alteraDataPagamento,
                                                                      @RequestHeader ("itau-pos-venda-teste") UUID headerItauPosVenda){
        logger.info("Header itau-pos-venda-teste {}", headerItauPosVenda);
        logger.info("Requisição para alteração de data de pagamento recebida", alteraDataPagamento);
        AditamentoDTO aditamentoDTO = alteracaoDataToAditamentoDTO.map(alteraDataPagamento);
        Aditamento aditamento = alteraDataUseCase.alteraDataPagamento(aditamentoDtoToAlteraDiaPagamento.map(aditamentoDTO));
        return this.getDataResponse(aditamento);
    }

    private ResponseEntity<AditamentoResponse> getDataResponse(Aditamento aditamento){
        return ResponseEntity.ok(aditamentoAditamentoResponseEntityMap.map(aditamento));
    }
}
