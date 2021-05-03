package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HomeController {

    @RequestMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    @RequestMapping("/set")
    String set(@RequestParam Map<String, String> allParams) {
        return allParams.toString();
    }
}

