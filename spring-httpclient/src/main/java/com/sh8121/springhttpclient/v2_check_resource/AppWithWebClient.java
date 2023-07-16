package com.sh8121.springhttpclient.v2_check_resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.web.reactive.function.client.WebClient;

public class AppWithWebClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var webClient = WebClient.create("http://localhost:8080");
        List<CompletableFuture<String>> completableFutures = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            completableFutures.add(webClient.get().uri("/hello?name=A").retrieve().bodyToMono(String.class).toFuture());
        }

        System.out.printf("Main ThreadGroup active count=%d\n", Thread.activeCount());

        completableFutures.get(0).thenApply(hello -> {
            System.out.printf("Reactor ThreadGroup active count=%d\n", Thread.activeCount());
            return hello;
        });

        System.out.printf("Used Memory is %d bytes\n", Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

        for (var completableFuture : completableFutures) {
            completableFuture.get();
        }
    }
}
