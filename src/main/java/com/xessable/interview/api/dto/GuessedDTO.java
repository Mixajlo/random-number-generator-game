package com.xessable.interview.api.dto;

import com.xessable.interview.model.Feedback;

public class GuessedDTO {
    private Feedback feedback;
    private int guessCount;
    private boolean finished;

    public GuessedDTO(Feedback feedback, int guessCount, boolean finished) {
        this.feedback = feedback;
        this.guessCount = guessCount;
        this.finished = finished;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public int getGuessCount() {
        return guessCount;
    }

    public boolean isFinished() {
        return finished;
    }
}
