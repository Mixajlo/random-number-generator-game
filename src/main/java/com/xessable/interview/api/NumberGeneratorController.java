package com.xessable.interview.api;

import com.xessable.interview.api.dto.GuessedNumber;
import com.xessable.interview.service.NumberGeneratorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public String startNewGame(HttpSession session) {
        return numberGeneratorService.startNewGame(session);
    }

    @PostMapping
    public String guessNumber(@RequestBody GuessedNumber number, HttpSession session) {
        return numberGeneratorService.guessNumber(number, session);
    }

    @GetMapping("/reset")
    public String resetGame(HttpServletRequest request) {
        return numberGeneratorService.resetGame(request.getSession());
    }
}
