package io.github.hadenlabs.poc_opentelemetry.productservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ErrorController {

    @GetMapping("/error")
    public String triggerError() {
        throw new RuntimeException("This is Infosis");
    }
}
