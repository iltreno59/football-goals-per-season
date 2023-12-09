package ru.mirea.kupriyanov.client;

import java.io.Serializable;
import java.time.LocalDate;

public class Match implements Serializable {

    private LocalDate matchDate;

    private int goals;

    public Match(LocalDate matchDate, int goals) {
        this.matchDate = matchDate;
        this.goals = goals;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public int getGoals() {
        return goals;
    }
}
