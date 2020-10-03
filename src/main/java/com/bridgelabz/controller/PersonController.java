package com.bridgelabz.controller;

import com.bridgelabz.configuration.MessageListenerContainerFactory;
import com.bridgelabz.dto.Response;
import com.bridgelabz.model.Person;
import com.bridgelabz.service.IPersonService;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;

@RestController
public class PersonController {

    @Autowired
    private IPersonService personService;

    @Autowired
    private MessageListenerContainerFactory messageListenerContainerFactory;

    @PostMapping(value = "person", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Response>> createPerson(@Valid @RequestBody Mono<Person> person,
                                                     @RequestParam("addressBookId") String addressBookId) {
        return person.flatMap(personObj -> personService.savePerson(personObj, addressBookId).map(savedPerson -> {
            Response response = new Response("Person added successfully", savedPerson);
            return ResponseEntity.ok(response);
        }));
    }

    @GetMapping(value = "/queue/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<?> receiveMessagesFromQueue(@PathVariable String name) {

        MessageListenerContainer mlc = messageListenerContainerFactory
                .createMessageListenerContainer("address");

        Flux<String> f = Flux.<String> create(emitter -> {
            mlc.setupMessageListener((MessageListener) m -> {
                String payload = new String(m.getBody());
                emitter.next(payload);
            });
            emitter.onRequest(v -> {
                mlc.start();
            });
            emitter.onDispose(() -> {
                mlc.stop();
            });
        });

        return Flux.interval(Duration.ofSeconds(5))
                .map(v -> "Messages from RabbitMQ")
                .mergeWith(f);
    }
}

