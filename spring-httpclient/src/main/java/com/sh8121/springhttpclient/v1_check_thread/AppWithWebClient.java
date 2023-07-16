package com.sh8121.springhttpclient.v1_check_thread;

import java.util.concurrent.ExecutionException;
import org.springframework.web.reactive.function.client.WebClient;

public class AppWithWebClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var webClient = WebClient.create("http://localhost:8080");
        var mono = webClient.get().uri("/hello?name=A").retrieve().bodyToMono(String.class);
        var completableFuture = mono.toFuture();
        completableFuture.thenApply(hello -> {
            System.out.printf("completableFuture.thenApply running at %s\n", Thread.currentThread());
            return hello;
        });
        completableFuture.get();
    }
}
