package com.xessable.interview.service.impl;

import com.xessable.interview.api.dto.GuessedNumber;
import com.xessable.interview.api.exceptions.NoGameFoundException;
import com.xessable.interview.model.GameState;
import com.xessable.interview.service.NumberGeneratorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

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
    public String guessNumber(GuessedNumber number, HttpSession session) {
        GameState state = getGameState(session);
        state.incrementGuessCount();
        return "Number guessed " + number.number() + " with failed tries " + state.getGuessCount();
    }

    private GameState getGameState(HttpSession session) {
        if(games.containsKey(session.getId())) {
            return games.get(session.getId());
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
