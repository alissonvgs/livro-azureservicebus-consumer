package com.estudo.azure.configuration;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBusProcessorClientConfiguration {

    @Value("${azure.servicebus.connection-string}")
    private String connectionString;

    @Value("${spring.cloud.azure.servicebus.entity-name}")
    private String queueName;

    @Autowired
    private MessageProcessor messageProcessor;

    @Bean
    public ServiceBusProcessorClient serviceBusProcessorClient() {
        ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .processor()
                .queueName(queueName)
                .processMessage(context -> messageProcessor.processMessage(context))
                .processError(context -> processError(context.getException()))
                .buildProcessorClient();

        processorClient.start();
        return processorClient;

    }

    private void processError(Throwable throwable) {
        System.err.println("Erro no processador: " + throwable.getMessage());
    }
}
