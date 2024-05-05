package com.rabbitmq.rabbitmq.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void enviaMensagem(String  nomeFi1a, Object mensagem){
        String mensagemJson;
        try {
            mensagemJson = objectMapper.writeValueAsString(mensagem);
            rabbitTemplate.convertAndSend(nomeFi1a, mensagemJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
