package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    Map<String, String> cache;

    public HomeController() {
        cache = new HashMap<>();
    }

    @RequestMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    @RequestMapping("/set")
    String set(@RequestParam Map<String, String> allParams) {
        cache.putAll(allParams);
        return String.format("Updated cache with the following values: %s", allParams.toString());
    }

    @RequestMapping("/get")
    String get(@RequestParam Map<String, String> allParams) {
        if (allParams.size() != 1) {
            throw  new IllegalArgumentException("Cannot retrieve multuple keys");
        }
        return allParams.get(allParams.keySet().iterator().next());
    }
}

