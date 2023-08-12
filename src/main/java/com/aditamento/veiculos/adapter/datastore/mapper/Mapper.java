package com.aditamento.veiculos.adapter.datastore.mapper;

import org.springframework.stereotype.Component;

@Component
public interface Mapper<I,O>{
     O map(I i);
}
