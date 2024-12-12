package com.practice.sec4;

import com.vinsguru.common.Util;
import com.vinsguru.sec04.helper.NameGenerator;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FluxSinkThreadSafety {
    public static void main(String[] args) {
        demo2();
    }

    public static void demo() {

        var list = new ArrayList<Integer>();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(runnable);
        }

        Util.sleepSeconds(3);
        log.info("list size: {}", list.size());
    }

    public static void demo2() {
        List<Integer> list = new ArrayList<>();
        Flux.create((FluxSink<Integer> sink) -> {
            Runnable runnable = () -> {
                for (int i = 0; i < 1000; i++) {
                    sink.next(i);
                }
            };

            for (int i = 0; i < 10; i++) {
                Thread.ofPlatform().start(runnable);
            }

        }).subscribe(list::add);

        Util.sleepSeconds(3);
        log.info("list size: {}", list.size());
    }

    private static void dem3(){
        var list = new ArrayList<String>();
        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(list::add);

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                generator.generate();
            }
        };
        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(runnable);
        }
        Util.sleepSeconds(3);
        log.info("list size: {}", list.size());
    }
}
