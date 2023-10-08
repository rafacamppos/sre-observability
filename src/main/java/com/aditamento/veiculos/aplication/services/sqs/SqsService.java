package com.aditamento.veiculos.aplication.services.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SqsService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AmazonSQS amazonSQS;




    public CreateQueueResult createQueue(final String queueName) {
        //TODO: create with createObjectRequest params
        CreateQueueRequest createRequest = new CreateQueueRequest(queueName)
               .addAttributesEntry("DelaySeconds", "60")
                .addAttributesEntry("MessageRetentionPeriod", "86400");
        CreateQueueResult ret = amazonSQS.createQueue(createRequest);
        logger.info("Lista criada com sucesso !! {}", ret);
        return ret;
    }

    public ListQueuesResult listQueues() {
        ListQueuesResult ret = amazonSQS.listQueues();
        logger.info("Lista consultada com sucesso !! {}", ret);
        return ret;
    }

    public DeleteQueueResult removeQueue(final String queueName) {
        return amazonSQS.deleteQueue(queueName);
    }

    public SendMessageResult publishMessage(final String queueUrl, final String message) {
        final ObjectMapper objectMapper = new ObjectMapper();
        SendMessageRequest sendMessageRequest = null;
        try {
            sendMessageRequest = new SendMessageRequest().withQueueUrl("http://localhost:4566/000000000000/" + queueUrl)
                    .withDelaySeconds(1)
                    .withMessageBody(message);
            SendMessageResult ret = amazonSQS.sendMessage(sendMessageRequest);
            logger.info("Mensagem produzida com sucesso !! {}", ret.getMD5OfMessageAttributes());
            return ret;
        }  catch (Exception e) {
            logger.error("Exception e : {}", e.getMessage());
        }
        return null;
    }



    //@SqsListener(value = QUEUE_NAME, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public List<Message> receiveMessages(final String queueUrl) {
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest();
        receiveMessageRequest.setQueueUrl(queueUrl);
        receiveMessageRequest.setWaitTimeSeconds(2);
        receiveMessageRequest.setMaxNumberOfMessages(5);
        List<Message> ret = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();
        if (ret.isEmpty()){
            logger.info("Não existe mensagem a ser consumida");
        }
        ret.forEach(message -> {
            amazonSQS.deleteMessage(queueUrl, message.getReceiptHandle());
            logger.info("Mensagem deletada com sucesso !! {}", message.getBody());
        });
        return ret;
    }
}
