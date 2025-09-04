package com.xessable.interview.service.impl;

import com.xessable.interview.api.dto.GuessedNumber;
import com.xessable.interview.api.exceptions.NoGameFoundException;
import com.xessable.interview.model.Feedback;
import com.xessable.interview.model.GameState;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class IMNumberGeneratorServiceTest {

    @Autowired
    private IMNumberGeneratorService service;

    @Mock
    private HttpSession session;

    @Test
    public void givenValidSession_whenGuessCorrectNumber_thenGameIsFinishedSuccessfully() {
        Mockito.when(session.getId()).thenReturn("valid-session");
        service.startNewGame(session);

        GameState initialGameState = service.guessNumber(new GuessedNumber(500), session);
        GameState resultGameState = service.guessNumber(new GuessedNumber(initialGameState.getTarget()), session);

        assertThat(resultGameState.isFinished()).isTrue();
        assertThat(resultGameState.getLastFeedback()).isEqualTo(Feedback.CORRECT);
        assertThat(resultGameState.getGuessCount()).isEqualTo(2);
        assertThat(resultGameState.getFinishedAt()).isNotNull();
    }

    @Test
    public void givenValidSession_whenGuessTooHigh_thenFeedbackIsTooHigh() {
        Mockito.when(session.getId()).thenReturn("valid-session");
        service.startNewGame(session);

        GameState initialGameState = service.guessNumber(new GuessedNumber(500), session);

        GameState resultGameState = service.guessNumber(new GuessedNumber(initialGameState.getTarget() + 1), session);

        assertThat(resultGameState.isFinished()).isFalse();
        assertThat(resultGameState.getLastFeedback()).isEqualTo(Feedback.TOO_HIGH);
        assertThat(resultGameState.getGuessCount()).isEqualTo(2);
    }

    @Test
    public void givenValidSession_whenGuessTooLow_thenFeedbackIsTooLow() {
        Mockito.when(session.getId()).thenReturn("valid-session");
        service.startNewGame(session);

        GameState initialGameState = service.guessNumber(new GuessedNumber(500), session);

        GameState resultGameState = service.guessNumber(new GuessedNumber(initialGameState.getTarget() - 1), session);

        assertThat(resultGameState.isFinished()).isFalse();
        assertThat(resultGameState.getLastFeedback()).isEqualTo(Feedback.TOO_LOW);
        assertThat(resultGameState.getGuessCount()).isEqualTo(2);
    }

    @Test
    public void givenFinishedGame_whenGuessNumber_thenThrowNoGameFoundException() {
        Mockito.when(session.getId()).thenReturn("valid-session");
        service.startNewGame(session);

        GameState initialGameState = service.guessNumber(new GuessedNumber(500), session);
        initialGameState.setFinished(true);

        assertThatThrownBy(() -> service.guessNumber(new GuessedNumber(500), session))
                .isInstanceOf(NoGameFoundException.class)
                .hasMessage("Game is already finished. Please start a new game");
    }

    @Test
    public void givenNoGameStarted_whenGuessNumber_thenThrowNoGameFoundException() {
        Mockito.when(session.getId()).thenReturn("valid-session");

        assertThatThrownBy(() -> service.guessNumber(new GuessedNumber(500), session))
                .isInstanceOf(NoGameFoundException.class)
                .hasMessage("Game is already finished. Please start a new game");
    }

    @Test
    public void givenValidSession_whenStartNewGame_thenGameStateInitialized() {
        Mockito.when(session.getId()).thenReturn("valid-session");
        String response = service.startNewGame(session);

        assertThat(response).contains("New game started. Random number is");
    }
}