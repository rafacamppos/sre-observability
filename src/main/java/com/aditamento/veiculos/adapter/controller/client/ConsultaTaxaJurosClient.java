package com.aditamento.veiculos.adapter.controller.client;

import com.aditamento.veiculos.adapter.controller.entity.ConsultaTaxaJurosResponse;
import com.aditamento.veiculos.adapter.beans.feign.ConsultaTaxaJurosClientConfiguration;
import com.aditamento.veiculos.adapter.datastore.entity.ConsultaTaxaJuros;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "taxa-juros", url = "${url.api.juros}",
        configuration = ConsultaTaxaJurosClientConfiguration.class)
public interface ConsultaTaxaJurosClient {
    @RequestMapping(method = RequestMethod.POST, value = "/calculo-juros", consumes = "application/json")
    ResponseEntity<ConsultaTaxaJurosResponse> consultaTaxaJuros(@RequestBody ConsultaTaxaJuros consultaTaxaJuros);
}
