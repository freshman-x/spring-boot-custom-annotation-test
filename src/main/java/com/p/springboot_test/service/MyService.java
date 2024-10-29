package com.p.springboot_test.service;

import com.p.springboot_test.aspect.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {
    public void processList(@Slice(size = 2) List<String> items) {
        // 这里的items已经被切片了
        System.out.println(items);
    }
}
