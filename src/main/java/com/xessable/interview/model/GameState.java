package com.xessable.interview.model;

import java.time.LocalDateTime;

public class GameState {

    private boolean finished;
    private final Integer target;
    private Integer guessCount;
    private Feedback lastFeedback;
    private final LocalDateTime startedAt;
    private LocalDateTime finishedAt;

    public GameState(Integer target) {
        this.target = target;
        this.guessCount = 0;
        this.finished = false;
        this.lastFeedback = null;
        this.startedAt = LocalDateTime.now();
        this.finishedAt = null;
    }

    public boolean isFinished() {
        return finished;
    }

    public Integer getTarget() {
        return target;
    }

    public Integer getGuessCount() {
        return guessCount;
    }

    public void incrementGuessCount() {
        this.guessCount++;
    }

    public Feedback getLastFeedback() {
        return lastFeedback;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void resetCounter() {
        this.guessCount = 0;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setLastFeedback(Feedback lastFeedback) {
        this.lastFeedback = lastFeedback;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }
}
