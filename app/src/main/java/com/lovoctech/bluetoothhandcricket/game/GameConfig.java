package com.lovoctech.bluetoothhandcricket.game;

public class GameConfig {

    private int wickets;
    private boolean aiOpponent;

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getWickets() {
        return wickets;
    }


    public void setAIOpponent(boolean aiOpponent) {
        this.aiOpponent = aiOpponent;
    }

    public boolean isAIOpponent() {
        return aiOpponent;
    }
}
