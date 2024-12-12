package com.practice.sec3;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

public class FluxInterval {
    public static void main(String[] args) {
        Flux.interval(java.time.Duration.ofMillis(100))
                .map(i -> Util.faker().name().firstName())
                .log()
                .take(10)
                .subscribe(System.out::println);

        Util.sleepSeconds(5);
    }
}
