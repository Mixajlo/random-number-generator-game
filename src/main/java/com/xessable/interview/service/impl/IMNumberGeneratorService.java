package com.xessable.interview.service.impl;

import com.xessable.interview.api.dto.GuessedNumber;
import com.xessable.interview.service.NumberGeneratorService;
import org.springframework.stereotype.Service;

@Service
public class IMNumberGeneratorService implements NumberGeneratorService {
    @Override
    public String startNewGame() {
        return "";
    }

    @Override
    public String generateNumber() {
        return "";
    }

    @Override
    public String guessNumber(GuessedNumber number) {
        return "Number guessed " + number.number();
    }
}
