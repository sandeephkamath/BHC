package com.lovoctech.bluetoothhandcricket.game;

import com.lovoctech.bluetoothhandcricket.ui.model.Choice;

import javax.inject.Inject;

public class Player {

    @Inject
    public Player() {

    }

    int score;
    int wickets;
    boolean battingOver = false;
    boolean batting = false;

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
        this.battingOver = battingOver;
    }

    public void score(Choice playerChoice) {
        score += playerChoice.getValue();
    }

    public void out() {
        wickets--;
    }

    public boolean isAllOut() {
        return wickets == 0;
    }

    public boolean isScoreEqual(Player player) {
        return score == player.getScore();
    }

    public boolean isScoreGreater(Player player) {
        return score > player.getScore();
    }
}
