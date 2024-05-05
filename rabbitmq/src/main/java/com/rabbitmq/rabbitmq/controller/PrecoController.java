package com.rabbitmq.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import constantes.RabbitMQConstantes;
import dto.PrecoDTO;
import com.rabbitmq.rabbitmq.services.RabbitMQService;

@RestController
@RequestMapping(value = "preco")
public class PrecoController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity<PrecoDTO> alteraPreco(@RequestBody PrecoDTO dto) {
        rabbitMQService.enviaMensagem(RabbitMQConstantes.FILA_PRECO, dto);
        return ResponseEntity.ok(dto);
    }

}
