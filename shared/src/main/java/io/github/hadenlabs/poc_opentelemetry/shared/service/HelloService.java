package io.github.hadenlabs.poc_opentelemetry.shared.service;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String getGreeting(){
        return "o/ World";
    }
}
