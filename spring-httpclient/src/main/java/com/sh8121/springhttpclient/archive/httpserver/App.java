package com.sh8121.springhttpclient.archive.httpserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

//        Flux.just(1,2,3,4)
//                .log()
//                .map(i -> i * 2);
    }
}
