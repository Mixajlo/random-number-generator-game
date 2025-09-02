package com.xessable.interview.service.impl;

import com.xessable.interview.api.dto.GuessedNumber;
import com.xessable.interview.api.exceptions.NoGameFoundException;
import com.xessable.interview.model.Feedback;
import com.xessable.interview.model.GameState;
import com.xessable.interview.service.NumberGeneratorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class IMNumberGeneratorService implements NumberGeneratorService {

    private final ConcurrentMap<String, GameState> games = new ConcurrentHashMap<>();

    @Override
    public String startNewGame(HttpSession session) {
        int randomNumber = generateRandomNumber();
        games.put(session.getId(), new GameState(randomNumber));
        return "New game started. Random number is " + randomNumber + " in session " + session.getId();
    }

    @Override
    public GameState guessNumber(GuessedNumber number, HttpSession session) {
        GameState state = getGameState(session);
        state.incrementGuessCount();
        if (number.number().equals(state.getTarget())) {
            state.setLastFeedback(Feedback.CORRECT);
            state.setFinished(true);
            state.setFinishedAt(LocalDateTime.now());
        } else if (number.number() > state.getTarget()) {
            state.setLastFeedback(Feedback.TOO_HIGH);
        } else {
            state.setLastFeedback(Feedback.TOO_LOW);
        }
        return state;
    }

    private GameState getGameState(HttpSession session) {
        if (games.containsKey(session.getId())) {
            GameState gameState = games.get(session.getId());
            if (gameState.isFinished()) {
                throw new NoGameFoundException("Game is already finished. Please start a new game");
            }
            return gameState;
        }
        throw new NoGameFoundException("No game started");
    }

    @Override
    public String resetGame(HttpSession session) {
        GameState state = getGameState(session);
        if (state == null) {
            throw new NoGameFoundException("No game found for session " + session.getId());
        }
        state.resetCounter();
        return "Reset game. New random number is " + generateRandomNumber() + " in session " + session.getId();
    }

    private Integer generateRandomNumber() {
        return new Random().nextInt(1001);
    }
}
