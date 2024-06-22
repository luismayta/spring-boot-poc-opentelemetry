package io.github.hadenlabs.poc_opentelemetry.shared.controller;
import io.github.hadenlabs.poc_opentelemetry.shared.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class HelloController {
    @Autowired
    public HelloService helloService;

    @GetMapping("/hello")
    public String sayHello() {
        return "data";
    }
}
