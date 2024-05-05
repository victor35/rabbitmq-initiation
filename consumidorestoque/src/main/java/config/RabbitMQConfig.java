package config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.databind.ObjectMapper;

import dto.EstoqueDTO;



public class RabbitMQConfig {


    @Bean
    @Scope("prototype")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(SimpleRabbitListenerContainerFactory factory, ObjectMapper objectMapper) {
        factory.setMessageConverter(jsonToMapMessageConverter(objectMapper));
        return factory;
    }

    @Bean
    public MessageConverter jsonToMapMessageConverter(ObjectMapper objectMapper) {
        Jackson2JsonMessageConverter messageConverter = new ImplicitJsonMessageConverter(objectMapper);
        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("*");
        classMapper.setDefaultType(EstoqueDTO.class);
        messageConverter.setClassMapper(classMapper);
        return messageConverter;
    }

    public static class ImplicitJsonMessageConverter extends Jackson2JsonMessageConverter {    
        public ImplicitJsonMessageConverter(ObjectMapper jsonObjectMapper) {
            super(jsonObjectMapper, "*");
        }    
        @Override
        public Object fromMessage(Message message) throws MessageConversionException {
            message.getMessageProperties().setContentType("application/json");
            return super.fromMessage(message);
        }
    }

}
