package com.xessable.interview.service.impl;

import com.xessable.interview.api.dto.GuessedNumber;
import com.xessable.interview.service.NumberGeneratorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class IMNumberGeneratorService implements NumberGeneratorService {

    @Override
    public String startNewGame(HttpSession session) {
        return "New game started. Random number is " + generateRandomNumber() + " in session " + session.getId();
    }

    @Override
    public String guessNumber(GuessedNumber number, HttpSession session) {
        return "Number guessed " + number.number() + " in session " + session.getId();
    }

    @Override
    public String resetGame(HttpSession session) {
        return "Reset game. New random number is " + generateRandomNumber() + " in session " + session.getId();
    }

    private Integer generateRandomNumber() {
        return new Random().nextInt(1001);
    }
}
