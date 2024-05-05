package com.rabbitmq.rabbitmq.connections;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import constantes.RabbitMQConstantes;

import jakarta.annotation.PostConstruct;

@Component
public class RabbitMQConnection {

    private static final String NOME_EXCHANGE = "amqp.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin){
        this.amqpAdmin=amqpAdmin;
    }

    private Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca) {
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona() {
        Queue filaEstoque = fila(RabbitMQConstantes.FILA_ESTOQUE);
        Queue filaPreco = fila(RabbitMQConstantes.FILA_PRECO);

        DirectExchange troca = trocaDireta();

        Binding ligacaoEstoque = relacionamento(filaEstoque, troca);
        Binding ligacaoPreco = relacionamento(filaPreco, troca);

        amqpAdmin.declareQueue(filaEstoque);
        amqpAdmin.declareQueue(filaPreco);

        amqpAdmin.declareExchange(troca);

        amqpAdmin.declareBinding(ligacaoEstoque);
        amqpAdmin.declareBinding(ligacaoPreco);
    }
}
