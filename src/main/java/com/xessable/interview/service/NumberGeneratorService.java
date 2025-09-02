package com.xessable.interview.service;

import com.xessable.interview.api.dto.GuessedNumber;

public interface NumberGeneratorService {

    String startNewGame(String sessionId);
    String guessNumber(GuessedNumber number, String sessionId);
    String resetGame(String sessionId);
}
