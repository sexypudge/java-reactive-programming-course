package com.practice.sec2;

import com.practice.sec2.client.ExternalClient;
import com.vinsguru.common.Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NonBlockingIO {
    public static void main(String[] args) {
        var client = new ExternalClient();
        log.info("starting");

        client.getProductName(1)
                .subscribe(Util.subscriber());

        for (int i = 1; i <= 5; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(2);
    }
}
