package com.practice.sec1.publisher;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class SubscriptionImpl implements Subscription {
    public static final int MAX_REQUESTS = 10;
    private final Subscriber<? super String> subscriber;
    private final Faker faker;

    private boolean isCancelled = false;
    private int count = 0;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }

    @Override
    public void request(long requested) {
        if (isCancelled) {
            return;
        }

        log.info("requested: {}", requested);

        if(requested > MAX_REQUESTS){
            this.subscriber.onError(new RuntimeException("validation failed"));
            this.isCancelled = true;
            return;
        }


        for (int i = 0; i < requested && count < MAX_REQUESTS; i++) {
            count++;
            subscriber.onNext(faker.internet().emailAddress());
        }

        if(count == MAX_REQUESTS){
            log.info("no more data to produce");
            this.subscriber.onComplete();
            this.isCancelled = true;
        }
    }

    @Override
    public void cancel() {
        log.info("cancelled!");
        this.isCancelled = true;
    }
}
