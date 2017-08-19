package com.jonwelzel.segment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SegmentApplication {
    public static void main(String ... args) {
        SpringApplication.run(SegmentApplication.class, args);
    }
}
