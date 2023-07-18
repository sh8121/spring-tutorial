package com.sh8121.springhttpclient.httpserver;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(String name) throws InterruptedException {
        Thread.sleep(2000L);
        return "hello, " + name;
    }

    @GetMapping("/numbers")
    public List<Integer> numbers() throws InterruptedException {
        Thread.sleep(2000L);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            result.add(i);
        }
        return result;
    }
}
