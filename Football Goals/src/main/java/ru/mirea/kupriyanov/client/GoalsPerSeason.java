package ru.mirea.kupriyanov.client;

import java.io.Serializable;
import java.util.List;

public class GoalsPerSeason implements Serializable {
    private int goals;
    private List<Match> matchList;

    public GoalsPerSeason(List<Match> matchList) {
        this.matchList = matchList;
        for(Match match: this.matchList){
            goals += match.getGoals();
        }
    }

    public int getGoals() {
        return goals;
    }

    public List<Match> getMatchList() {
        return matchList;
    }
}
