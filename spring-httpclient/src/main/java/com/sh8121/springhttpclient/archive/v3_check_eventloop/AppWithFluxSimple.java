package com.sh8121.springhttpclient.archive.v3_check_eventloop;

import org.springframework.web.reactive.function.client.WebClient;

public class AppWithFluxSimple {

    public static void main(String[] args) throws InterruptedException {
        var webClient = WebClient.create("http://localhost:8080");
        var flux = webClient.get().uri("/numbers").retrieve().bodyToFlux(Integer.class);

        var threads = Thread.getAllStackTraces()
            .keySet().stream().toList();
        System.out.printf("Threads: %s\n", threads);

        flux.subscribe(number -> {
            System.out.printf("Consumer1 get %d At %s\n", number, Thread.currentThread());
        });
        flux.subscribe(number -> {
            System.out.printf("Consumer2 get %d At %s\n", number, Thread.currentThread());
        });

        threads = Thread.getAllStackTraces()
            .keySet().stream().toList();
        System.out.printf("Threads: %s\n", threads);

        Thread.sleep(3000L);
    }
}
