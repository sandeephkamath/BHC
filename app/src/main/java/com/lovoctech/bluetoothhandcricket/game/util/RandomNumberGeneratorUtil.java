package com.lovoctech.bluetoothhandcricket.game.util;

import com.lovoctech.bluetoothhandcricket.game.GameConfig;

import java.util.Random;

public class RandomNumberGeneratorUtil {

    private GameConfig gameConfig;

    public RandomNumberGeneratorUtil(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    public int getRandomNumber() {

        int max = 6, min = 1;

        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}