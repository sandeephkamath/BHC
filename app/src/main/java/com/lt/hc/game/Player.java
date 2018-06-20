package com.lt.hc.game;

public class Player {

    int score;
    int wickets;
    boolean battingOver;
    boolean batting;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public void setBatting(boolean batting) {
        this.batting = batting;
    }

    public boolean isBatting() {
        return batting;
    }

    public boolean isBattingOver() {
        return battingOver;
    }

    public void setBattingOver(boolean battingOver) {
        battingOver = battingOver;
    }

    public void score(int playerScore) {
        score += playerScore;
    }

    public void out() {
        wickets--;
    }

    public boolean isAllOut() {
        return wickets == 0;
    }
}
