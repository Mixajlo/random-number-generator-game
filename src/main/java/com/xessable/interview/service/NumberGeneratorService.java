package com.xessable.interview.service;

import com.xessable.interview.api.dto.GuessedNumber;
import jakarta.servlet.http.HttpSession;

public interface NumberGeneratorService {

    String startNewGame(HttpSession session);
    String guessNumber(GuessedNumber number, HttpSession session);
    String resetGame(HttpSession session);
}
