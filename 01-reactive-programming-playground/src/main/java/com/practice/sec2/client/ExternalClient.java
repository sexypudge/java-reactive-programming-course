package com.practice.sec2.client;

import com.practice.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalClient extends AbstractHttpClient {
    /**
     * When this method is invoked, we create a Mono which can send a request to the server.
     * But the actual HTTP request is sent, only when it is subscribed
     *
     * The destination server will respond with a mono as well.
     * We can use the mono to get the response from the server.
     * @return
     */
    public Mono<String> getProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()// Flux of string
                .next(); // next() means get the first String
        // when use Webflux, webflux will do everything: deserialize streams into object
    }

    public Flux<String> getStockStream() {
        return this.httpClient.get()
                .uri("/demo02/stock/stream")
                .responseContent()
                .asString();
    }
}
