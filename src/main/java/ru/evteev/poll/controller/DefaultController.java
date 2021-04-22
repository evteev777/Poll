package ru.evteev.poll.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.evteev.poll.service.PollService;

@AllArgsConstructor
@RestController
public class DefaultController {

    private final PollService pollService;

    @GetMapping("/")
    public String index(){
        return "Welcome to our Poll service";
    }
}
