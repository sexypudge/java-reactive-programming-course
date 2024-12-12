package com.practice.sec3.helper;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

public class NameGenerator {

    public static List<String> getNames(int count) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> generateName())
                .toList();
    }


    public static Flux<String> getNamesFlux(int i) {
        return Flux.range(1, i)
                .map(l -> generateName());
    }

    private static String generateName() {
        Util.sleepSeconds(1);
        return Util.faker().name().firstName();
    }
}
