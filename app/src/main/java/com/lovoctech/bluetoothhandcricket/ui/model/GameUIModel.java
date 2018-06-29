package com.lovoctech.bluetoothhandcricket.ui.model;

import android.arch.lifecycle.LiveData;

public class GameUIModel extends LiveData{

    private int playerScore;

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
}
