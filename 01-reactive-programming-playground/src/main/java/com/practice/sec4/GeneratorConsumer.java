package com.practice.sec4;

import com.vinsguru.common.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class GeneratorConsumer implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink;

    @Override
    public void accept(FluxSink<String> fluxSink) {
        this.fluxSink = fluxSink;

        // Add completion and error handling
        fluxSink.onDispose(() -> {
            // Cleanup resources when the flux is disposed
            this.fluxSink = null;
        });

        fluxSink.onCancel(() -> {
            // Handle cancellation
            this.fluxSink = null;
        });

        // Optional: Add backpressure strategy
        fluxSink.onRequest(n -> {
            // Handle backpressure when subscriber requests elements
            generate();
        });
    }

    public void generate() {
        fluxSink.next(Util.faker().country().name());
    }

}
