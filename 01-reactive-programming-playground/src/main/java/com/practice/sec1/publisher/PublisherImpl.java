package com.practice.sec1.publisher;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

@Slf4j
public class PublisherImpl implements Publisher<String> {
    @Override
    public void subscribe(Subscriber subscriber) {

        subscriber.onSubscribe(new SubscriptionImpl(subscriber));
    }
}
