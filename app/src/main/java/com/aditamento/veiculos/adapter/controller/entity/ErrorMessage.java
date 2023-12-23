package com.aditamento.veiculos.adapter.controller.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
    private int status;
    private String message;
    private String url;
}
