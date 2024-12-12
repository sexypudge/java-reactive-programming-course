package com.practice.sec3.assignment;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StockSubscriber implements Subscriber<String> {

    private final List<Integer> purchasedStock = new ArrayList<>();

    private static int BALANCE = 1000;
    private static int PROFIT = 0;

    @Getter
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(200);
    }

    @Override
    public void onNext(String s) {
        var stockPrice = Integer.parseInt(s);
        if (stockPrice < 90
                && BALANCE > stockPrice) {
            purchasedStock.add(stockPrice);
            BALANCE -= stockPrice;
        }

        if (stockPrice > 110) {
            for(var stock : purchasedStock) {
                PROFIT += stockPrice;
            }

            purchasedStock.clear();
            subscription.cancel();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("error", throwable);
    }

    @Override
    public void onComplete() {
        log.info("onComplete: profit {}", PROFIT);
    }
}
