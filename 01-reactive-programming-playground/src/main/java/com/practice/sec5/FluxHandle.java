package com.practice.sec5;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

public class FluxHandle {

    public static void main(String[] args) {
        Flux.range(0, 10)
                .handle((item, sink) -> {
                    if(item % 2 == 0){
                        sink.next(item);
                    }
                })
                .cast(Integer.class)
                .subscribe(Util.subscriber());

    }
}
