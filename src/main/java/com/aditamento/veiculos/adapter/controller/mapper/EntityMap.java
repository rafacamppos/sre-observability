package com.aditamento.veiculos.adapter.controller.mapper;

import org.springframework.stereotype.Component;

@Component
public interface EntityMap<I,O>{
     O map(I i);
}
