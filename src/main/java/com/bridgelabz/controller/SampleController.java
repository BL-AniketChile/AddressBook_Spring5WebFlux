package com.bridgelabz.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.print.attribute.standard.Media;
import java.time.Duration;

@RestController
public class SampleController {

    @GetMapping(value = "/flux", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> getFlux() {
        return Flux.just("Shweta", "Aniket", "Rohan", "Rohini");
    }

    @GetMapping(value = "/fluxstream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> getFluxStream(){
        return Flux.range(0, 20).delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping(value = "/fluxstreams", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> getFluxStreams() {
        return Flux.interval(Duration.ofSeconds(1)).log();
    }


}
