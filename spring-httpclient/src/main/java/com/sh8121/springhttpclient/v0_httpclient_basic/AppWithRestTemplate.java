package com.sh8121.springhttpclient.v0_httpclient_basic;

import java.time.LocalDateTime;
import org.springframework.web.client.RestTemplate;

public class AppWithRestTemplate {

    public static void main(String[] args) {
        System.out.println("Main Started At " + LocalDateTime.now());
        var restTemplate = new RestTemplate();
        String hello1 = restTemplate.getForObject("http://localhost:8080/hello?name=A", String.class);
        String hello2 = restTemplate.getForObject("http://localhost:8080/hello?name=B", String.class);
        String hello3 = restTemplate.getForObject("http://localhost:8080/hello?name=C", String.class);
        System.out.printf("hello1=%s, hello2=%s, hello3=%s\n", hello1, hello2, hello3);
        System.out.println("Main Ended At " + LocalDateTime.now());
    }
}
