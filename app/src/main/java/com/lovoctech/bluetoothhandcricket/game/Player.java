package com.lovoctech.bluetoothhandcricket.game;

import com.lovoctech.bluetoothhandcricket.ui.model.Choice;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

public class Player {

    @Inject
    public Player() {

    }

    private int score;
    private int wickets;
    private boolean battingOver;
    private boolean batting;
    private int totalWickets;
    private ArrayList<Integer> scores;

    public int getScore() {
        return score;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
        totalWickets = wickets;
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
        if (scores == null) {
            scores = new ArrayList<>(wickets);
            scores.add(score);
        } else {
            int currentPlayerScore = score - scores.get(scores.size() - 1);
            scores.add(currentPlayerScore);
        }

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

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public int getTotalWickets() {
        return totalWickets;
    }

    @Override
    public String toString() {
        return "Player{" +
                "\n\tscore=" + score +
                ",\n\twickets= " + (totalWickets - wickets) + " / " + totalWickets +
                ",\n\tbattingOver=" + battingOver +
                ",\n\tbatting=" + batting +
                ",\n\tscores=" + (scores == null ? score : Arrays.deepToString(scores.toArray())) +
                "\n}";
    }
}
