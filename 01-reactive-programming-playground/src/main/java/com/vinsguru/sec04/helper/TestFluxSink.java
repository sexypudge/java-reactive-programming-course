package com.vinsguru.sec04.helper;

import com.vinsguru.common.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class TestFluxSink implements Consumer<FluxSink<String>> {
    @Override
    public void accept(FluxSink<String> fluxSink) {
        String country;
        do {
            country = Util.faker().country().name();
            fluxSink.next(country);
        } while (!country.equalsIgnoreCase("canada"));
        fluxSink.complete();
    }
}
