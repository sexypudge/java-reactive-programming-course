package com.practice.sec3.assignment;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;

@Slf4j
public class StockSubscription implements Subscription {
    private boolean isCancelled = false;
    private static final int MAX_ITEMS = 20;
    private final StockSubscriber subscriber;

    public StockSubscription(StockSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void request(long l) {
        if(isCancelled){
            return;
        }

        if(l > MAX_ITEMS){
            this.subscriber.onError(new RuntimeException("validation failed"));
            this.isCancelled = true; // if error, no send item anymore
            return;
        }
    }

    @Override
    public void cancel() {
        log.info("canceled");
        this.isCancelled = true;
    }
}
