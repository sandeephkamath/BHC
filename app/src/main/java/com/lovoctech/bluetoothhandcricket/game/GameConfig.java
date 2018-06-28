package com.lovoctech.bluetoothhandcricket.game;

public class GameConfig {

    private int wickets;
    private boolean aiOpponent;
    private int overs;

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public int getOvers() {
        return overs;
    }

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
