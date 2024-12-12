package com.practice.sec4;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

public class FluxCreateRefactor {
    public static void main(String[] args) {
        var generator = new GeneratorConsumer();

        var flux = Flux.create(generator);
        flux.subscribe(Util.subscriber());

        Util.sleepSeconds(4);
    }
}
