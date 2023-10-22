package com.aditamento.veiculos.aplication.services.sqs;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Profile("teste")
public class ListenerSqs {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String QUEUE_NAME = "upload-file-event-sqs";

    @SqsListener(value = QUEUE_NAME,
            deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void receive(@Headers Map<String, String> headers, String message) {

        logger.info("objectKey:: {}",message);
    }
}
