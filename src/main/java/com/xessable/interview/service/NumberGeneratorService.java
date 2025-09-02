package com.xessable.interview.service;

import com.xessable.interview.api.dto.GuessedNumber;

public interface NumberGeneratorService {

    String startNewGame();
    String generateNumber();
    String guessNumber(GuessedNumber number);
}
