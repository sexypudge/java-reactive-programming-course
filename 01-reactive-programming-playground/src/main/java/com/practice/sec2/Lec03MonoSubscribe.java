package com.practice.sec2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

/*
    To discuss some of the subscribe overloaded methods
 */
public class Lec03MonoSubscribe {

    private static final Logger log = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String[] args) {

        var mono = Mono.just(1)
                .map(i  -> i + 10);
        mono.subscribe(
                i -> log.info("received: {}", i),
                err -> log.error("error", err),
                () -> log.info("completed"),
                subscription -> subscription.request(1)
//                subscription: This is the "onSubscribe" handler
//                It controls backpressure (flow control)
//                It requests 1 item from the publisher
//                This is where you can customize how many items you want to receive
        );

    }

}
