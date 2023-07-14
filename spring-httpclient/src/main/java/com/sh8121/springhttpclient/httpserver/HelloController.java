package com.sh8121.springhttpclient.httpserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(String name) throws InterruptedException {
        Thread.sleep(2000L);
        return "hello, " + name;
    }
}
