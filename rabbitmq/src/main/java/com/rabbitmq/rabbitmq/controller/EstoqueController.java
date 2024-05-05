package com.rabbitmq.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import constantes.RabbitMQConstantes;
import dto.EstoqueDTO;
import com.rabbitmq.rabbitmq.services.RabbitMQService;

@RestController
@RequestMapping(path = "estoque")
public class EstoqueController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity<EstoqueDTO> alteraEstoque(@RequestBody EstoqueDTO dto) {
        rabbitMQService.enviaMensagem(RabbitMQConstantes.FILA_ESTOQUE, dto);
        return ResponseEntity.ok(dto);
    }

}
