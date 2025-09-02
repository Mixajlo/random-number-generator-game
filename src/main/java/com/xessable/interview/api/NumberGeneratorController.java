package com.xessable.interview.api;

import com.xessable.interview.api.dto.GuessedNumber;
import com.xessable.interview.service.NumberGeneratorService;
import com.xessable.interview.service.impl.IMNumberGeneratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NumberGeneratorController {

    private final NumberGeneratorService numberGeneratorService;

    public NumberGeneratorController(NumberGeneratorService numberGeneratorService) {
        this.numberGeneratorService = numberGeneratorService;
    }

    @GetMapping
    public String startNewGame() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping
    public String guessNumber(@RequestBody GuessedNumber number) {
        return numberGeneratorService.guessNumber(number);
    }
}
