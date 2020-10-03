package com.bridgelabz.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ApplicationConfig {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @PostConstruct
    public void setupQueueDestinations() {
        Exchange ex = ExchangeBuilder.directExchange("address-data-exchange")
                            .durable(true)
                            .build();
        amqpAdmin.declareExchange(ex);
        Queue q = QueueBuilder.durable("address")
                            .build();
        amqpAdmin.declareQueue(q);
        Binding b = BindingBuilder.bind(q)
                            .to(ex)
                            .with("address")
                            .noargs();
        amqpAdmin.declareBinding(b);
    }
}
