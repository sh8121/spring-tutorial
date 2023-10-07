package com.sh8121.springhttpclient.archive.v1_check_thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import org.springframework.web.client.RestTemplate;

public class AppWithRestTemplate {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var restTemplate = new RestTemplate();
        var executorService = Executors.newFixedThreadPool(100);
        var completableFuture1 = CompletableFuture.supplyAsync(
            () -> {
                System.out.printf("completableFuture1.supplyAsync running at %s\n", Thread.currentThread());
                return restTemplate.getForObject("http://localhost:8080/hello?name=A", String.class);
            },
            executorService);
        var completableFuture2 = CompletableFuture.supplyAsync(
            () -> {
                System.out.printf("completableFuture2.supplyAsync running at %s\n", Thread.currentThread());
                return restTemplate.getForObject("http://localhost:8080/hello?name=A", String.class);
            });
        completableFuture1.thenApply(hello -> {
            System.out.printf("completableFuture1.thenApply running at %s\n", Thread.currentThread());
            return hello;
        });
        completableFuture2.thenApply(hello -> {
            System.out.printf("completableFuture2.thenApply running at %s\n", Thread.currentThread());
            return hello;
        });

        completableFuture1.get();
        completableFuture2.get();

        executorService.shutdown();
    }
}
