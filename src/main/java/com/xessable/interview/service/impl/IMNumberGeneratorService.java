package com.xessable.interview.service.impl;

import com.xessable.interview.api.dto.GuessedNumber;
import com.xessable.interview.service.NumberGeneratorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class IMNumberGeneratorService implements NumberGeneratorService {

    @Override
    public String startNewGame(String sessionId) {
        return "New game started. Random number is " + generateRandomNumber()+ " in session " + sessionId;
    }

    @Override
    public String guessNumber(GuessedNumber number, String sessionId) {
        return "Number guessed " + number.number() + " in session " + sessionId;
    }

    @Override
    public String resetGame(String sessionId) {
        return "Reset game. New random number is " + generateRandomNumber()+ " in session " + sessionId;
    }

    private Integer generateRandomNumber() {
        return new Random().nextInt(1001);
    }
}
