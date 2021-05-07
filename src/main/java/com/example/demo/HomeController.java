package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    private static String FILENAME = "storage.txt";
    Map<String, String> cache;
    FileWriter writer;

    public HomeController() throws IOException {
        cache = new HashMap<>();
        writer = new FileWriter(FILENAME, false);
    }

    @RequestMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    @RequestMapping("/set")
    String set(@RequestParam Map<String, String> allParams) throws IOException {
        // cache.putAll(allParams);
        BufferedWriter currentWriter = new BufferedWriter(new FileWriter(FILENAME, true));
        currentWriter.append("\n");
        for (String key : allParams.keySet()) {
            currentWriter.append(key + "=" + allParams.get(key));
        }
        currentWriter.close();
        return String.format("Updated file with the following values: %s", allParams.toString());
    }

    @RequestMapping("/get")
    String get(@RequestParam("key") String key)  throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(FILENAME));
        String last = null;
        String line;

        while ((line = input.readLine()) != null) {
            String[] split = line.split("=");
            if (split[0].equals(key)) {
                last = line;
            }
        }

        if (last == null) {
            return "No entry found";
        }
        return "Last entry was " + last;
    }
}

