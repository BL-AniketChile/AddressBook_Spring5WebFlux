package com.bridgelabz;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

public class FluxAndMonoTest {

    @Test
    void fluxTest() {

        Flux<String> stringFlux = Flux.just("Shweta", "Aniket", "Rohan", "Rohini")
//                .concatWith(Flux.error(new RuntimeException("Exception occurred...!!!")))
//                .concatWith(Flux.just("Zarana", "Sugandha"))
                .log();

        stringFlux.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()),
                () -> System.out.println("All the data received successfully...!! On complete event..!!"));
    }

    @Test
    void monoTest() {

        Mono<String> stringMono = Mono.just("Shweta").log();
        stringMono.subscribe(System.out::println);

    }

    @Test
    void mapTest() {

        Flux<String> stringFlux = Flux.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon").log();

        stringFlux.map(string -> string.concat(" rays"))

                .subscribe(System.out::println);
    }

    @Test
    void flatMapTest() {

        Flux<String> stringFlux = Flux.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon").log();

        Flux<String> fluxString = stringFlux.flatMap(s -> Flux.fromArray(s.split("")));

                fluxString.subscribe(System.out::println);
    }

    @Test
    void reduceTest() {

        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4, 5, 6).log();
        integerFlux.reduce((total, number) -> total + number)
                .subscribe(System.out::println);
    }

    @Test
    void scanTest() {
        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4, 5, 6).log();
        integerFlux.scan((total, number) -> total + number)
                .subscribe(System.out::println);

    }

    @Test
    void anyTest() {
        Flux.just("2016-01-01", "2016-05-02", "2016-09-12", "2016-11-03").log()
                .map(LocalDate::parse)
                .any(month -> month.getMonthValue() >= 12)
                .subscribe(System.out::println);

    }

    @Test
    void doOnNextTest() {

        Flux<String> stringFlux = Flux.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        stringFlux.doOnNext(string -> System.out.println("Processing data  " + string))
//                .map(string -> string.concat(" rays"))
                .subscribe(System.out::println);
    }

    @Test
    void doOnCompleteTest() {

        Flux<String> stringFlux = Flux.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        stringFlux.doOnComplete(() -> System.out.println("Processing of data is completed..!!"))
                .map(string -> string.concat(" rays"))
                .subscribe(System.out::println);
    }

    @Test
    void zipTest() {

        Flux<String> stringFlux = Flux.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Flux<Integer> range = Flux.range(1, 6);

        Flux.zip(stringFlux, range, (s, i) -> i + "-" + s).subscribe(System.out::println);

    }

    @Test
    void groupByTest() {

        Flux<String> stringFlux = Flux.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        Flux<GroupedFlux<Integer, String>> groupedFlux = stringFlux.groupBy(s -> s.length());

        Flux<List<String>> listFlux = groupedFlux.flatMap(grouped -> grouped.collectList());

        listFlux.subscribe(System.out::println);

    }
}
