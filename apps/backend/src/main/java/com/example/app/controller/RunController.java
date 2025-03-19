package com.example.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class RunController {
    @GetMapping("/")
    public Mono<String> home() {
        return Mono.just("Cristiano Ronaldo");
    }
}
