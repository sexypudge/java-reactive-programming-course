package com.practice.sec2;

import com.practice.sec1.subscriber.SubscriberImpl;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public class MonoJust {
    public static void main(String[] args) {
        var mono = Mono.just("ball");
        var subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);

        subscriber.getSubscription().request(10);

        save(mono);
    }

    private static void save(Publisher<String> publisher) {

    }
}
