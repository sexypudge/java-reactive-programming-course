package com.practice.sec1;

import com.practice.sec1.publisher.PublisherImpl;
import com.practice.sec1.subscriber.SubscriberImpl;

import java.time.Duration;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        // 1. publisher does not produce data unless subscriber requests for it.
        subscriber.getSubscription().request(3); // 2. publisher will produce only <= subscriber requested items. publisher can also produce 0 items!
        Thread.sleep(Duration.ofSeconds(2));

        // 3. subscriber can cancel the subscription.
        // producer should stop at that moment as subscriber is no longer interested in consuming the data
        subscriber.getSubscription().cancel();

        subscriber.getSubscription().request(11);
        Thread.sleep(Duration.ofSeconds(2));

        // 4. producer can send the error signal
        subscriber.getSubscription().request(11);
        Thread.sleep(Duration.ofSeconds(2));

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
    }
}
