package com.sh8121.springhttpclient.v0_basic_concept;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import org.springframework.web.reactive.function.client.WebClient;

public class AppWithWebClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Main Started At " + LocalDateTime.now());

        var webClient = WebClient.create("http://localhost:8080");
        var mono1 = webClient.get().uri("/hello?name=A").retrieve().bodyToMono(String.class);
        var mono2 = webClient.get().uri("/hello?name=B").retrieve().bodyToMono(String.class);
        var mono3 = webClient.get().uri("/hello?name=C").retrieve().bodyToMono(String.class);

        var future1 = mono1.toFuture();
        var future2 = mono2.toFuture();
        var future3 = mono3.toFuture();

        var hello1 = future1.get();
        var hello2 = future2.get();
        var hello3 = future3.get();

        System.out.printf("hello1=%s, hello2=%s, hello3=%s\n", hello1, hello2, hello3);
        System.out.println("Main Ended At " + LocalDateTime.now());
    }
}
