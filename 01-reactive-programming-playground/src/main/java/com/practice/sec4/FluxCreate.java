package com.practice.sec4;

import com.practice.sec1.subscriber.SubscriberImpl;
import com.vinsguru.common.Util;
import com.vinsguru.sec04.helper.TestFluxSink;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Slf4j
public class FluxCreate {
    public static void main(String[] args) {
//        Flux.create(fluxSink -> {
//            fluxSink.next(1);
//            fluxSink.next(2);
//            fluxSink.next(3);
//            fluxSink.complete();
//        }).subscribe(Util.subscriber());
//
//        Flux.create(fluxSink -> {
//            String country;
//            do {
//                country = Util.faker().country().name();
//                fluxSink.next(country);
//            } while (!country.equalsIgnoreCase("canada"));
//            fluxSink.complete();
//        }).subscribe(Util.subscriber());
//
//        Flux.create(new TestFluxSink())
//                .subscribe(Util.subscriber());

        defaultBehaviour();
    }

    public static void defaultBehaviour() {
        var sub = new SubscriberImpl();
        Flux.<String>create(fluxSink-> {
            for (int i = 0; i < 10; i++) {
                var name = Util.faker().name().firstName();
                log.info("Emitting {}", name);
                fluxSink.next(name);
            }
            fluxSink.complete();
        }).subscribe(sub);

        sub.getSubscription().request(3);
        Util.sleepSeconds(2);
    }
}
