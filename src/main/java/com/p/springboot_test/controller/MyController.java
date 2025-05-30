package com.p.springboot_test.controller;

import com.p.springboot_test.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping("/test")
    public void test(@RequestParam List<String> items) {
        List<String> input = Arrays.asList("A", "B", "C", "D", "E", "F", "G");
        myService.processList(input);
    }
}

