package com.practice.sec3.assignment;

import com.practice.sec2.client.ExternalClient;
import com.vinsguru.common.Util;

public class Main {
    public static void main(String[] args) {
        var client = new ExternalClient();
        var stockStreamPublisher = client.getStockStream();
        var stockSubscriber = new StockSubscriber();

        stockStreamPublisher.log().subscribe(stockSubscriber);

        Util.sleepSeconds(200);
    }
}
