package com.p.springboot_test.service;
import com.p.springboot_test.aspect.Slice;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MyService {
    public void processList(@Slice(start = 1, end = 3) List<String> items) {
        // 这里的items已经被切片了
        items.forEach(System.out::println);
    }
}
