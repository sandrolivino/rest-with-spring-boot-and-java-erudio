package br.com.erudio.restspringboot.controllers;

import br.com.erudio.restspringboot.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Contador: %s - Saudação: Hello, %s %s!";
    private final AtomicLong id = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name,
                             @RequestParam(value = "surname") String surname) {
        return new Greeting(id.incrementAndGet(), String.format(template, id, name, surname));
    }
}
