package com.aditamento.veiculos.adapter.beans.aws.sqs;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SqsConfiguration {

    @Bean
    @Primary
    public AmazonSQSAsync amazonSqsAsync() {
        final var clientBuilder = AmazonSQSAsyncClientBuilder.standard();
        AmazonSQSAsyncClientBuilder.EndpointConfiguration endpointConfiguration =
                new AwsClientBuilder.EndpointConfiguration("http://localhost:4566",Regions.SA_EAST_1.getName());
        clientBuilder.setEndpointConfiguration(endpointConfiguration);
        final AWSCredentials credentials = new BasicAWSCredentials("AWS_ACCESS_KEY", "AWS_SECRET");
        clientBuilder.setCredentials(new AWSStaticCredentialsProvider(credentials));
        return clientBuilder.build();
    }

    private AwsClientBuilder.EndpointConfiguration getEndpointConfiguration(String url) {
        return new AwsClientBuilder.EndpointConfiguration(url, Regions.SA_EAST_1.getName());
    }

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(
            AmazonSQSAsync amazonSqsAsync) {
        final var listenerContainerFactory = new SimpleMessageListenerContainerFactory();
        listenerContainerFactory.setAutoStartup(true);
        listenerContainerFactory.setAmazonSqs(amazonSqsAsync());
        listenerContainerFactory.setMaxNumberOfMessages(1);
        return listenerContainerFactory;
    }



    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(
            AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }
}
