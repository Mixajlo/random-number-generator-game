package com.xessable.interview.service;

import com.xessable.interview.api.dto.GuessedNumber;
import com.xessable.interview.model.GameState;
import jakarta.servlet.http.HttpSession;

public interface NumberGeneratorService {

    String startNewGame(HttpSession session);
    GameState guessNumber(GuessedNumber number, HttpSession session);
    String resetGame(HttpSession session);
}
