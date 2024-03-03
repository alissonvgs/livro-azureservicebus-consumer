package com.estudo.azure.configuration;

import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import com.estudo.domain.LivroDTO;
import com.estudo.service.LivroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class MessageProcessor {
    private final LivroService livroService;

    @Autowired
    public MessageProcessor(LivroService livroService) {
        this.livroService = livroService;
    }

    public void processMessage(ServiceBusReceivedMessageContext context)  {
        ServiceBusReceivedMessage mensagem = context.getMessage();

        byte[] body = context.getMessage().getBody().toBytes();
        String json = new String(body, StandardCharsets.UTF_8);
        System.out.println("Corpo da mensagem: " + json);

        LivroDTO livroDTO = jsonToLivroDTO(json);

        livroService.create(livroDTO);
        context.complete();
        System.out.println("CONSUMER:: Mensagem recebida" + livroDTO.toString());
    }

    public LivroDTO jsonToLivroDTO(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        LivroDTO LivroDTO = null;
        try {
            LivroDTO = objectMapper.readValue(json, LivroDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return LivroDTO;
    }


}