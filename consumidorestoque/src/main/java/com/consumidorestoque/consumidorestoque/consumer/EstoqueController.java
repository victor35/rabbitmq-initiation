package com.consumidorestoque.consumidorestoque.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import constantes.RabbitMQConstantes;
import dto.EstoqueDTO;

@Component
public class EstoqueController {

    @RabbitListener(queues = RabbitMQConstantes.FILA_ESTOQUE)
    private void consumidor(EstoqueDTO dto){
        System.out.println(dto.codigoProduto);
        System.out.println(dto.quantidade);
        System.out.println("----------------------------------------------------");
    }

}
