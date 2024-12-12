package com.practice.sec3;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxAndMonoConversion {
    public static void main(String[] args) {
        var mono = Mono.just("ball");
        saveFluxString(mono.flux());
        saveFluxString(Flux.from(mono));

        var flux = Flux.just("ball");
        var monoNext = flux.next();
        saveMonoString(Mono.from(flux));
        saveMonoString(monoNext);

    }
    private static void saveFluxString(Flux<String> stringFlux) {
        stringFlux.subscribe(System.out::println);
    }

    private static void saveMonoString(Mono<String> stringMono) {
        stringMono.subscribe(System.out::println);
    }


    private static Mono<String> getUsername(int userId){
        return switch (userId){
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty(); // null
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }


}
