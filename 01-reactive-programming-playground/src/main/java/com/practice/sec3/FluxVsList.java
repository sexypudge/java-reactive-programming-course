package com.practice.sec3;

import com.practice.sec1.subscriber.SubscriberImpl;
import com.practice.sec3.helper.NameGenerator;
import com.vinsguru.common.Util;

public class FluxVsList {
    public static void main(String[] args) {
        var list = NameGenerator.getNames(5);
        list.forEach(System.out::println);

        var flux = NameGenerator.getNamesFlux(5);
        flux.subscribe(System.out::println);

        var subscriber = new SubscriberImpl();
        NameGenerator.getNamesFlux(5).subscribe(subscriber);
        subscriber.getSubscription().request(3);

        subscriber.getSubscription().cancel();

    }

}

