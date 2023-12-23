package com.aditamento.veiculos.adapter.controller.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SqsMessageRequest {

    @JsonProperty(value = "queue_url")
    private String queueUrl;
    @JsonProperty(value = "message")
    private String message;
}
