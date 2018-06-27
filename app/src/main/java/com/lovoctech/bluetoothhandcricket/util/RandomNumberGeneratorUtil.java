package com.lovoctech.bluetoothhandcricket.util;

import com.lovoctech.bluetoothhandcricket.game.GameConfig;

import java.util.Random;

public class RandomNumberGeneratorUtil {

    private GameConfig gameConfig;

    public RandomNumberGeneratorUtil(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    public static int getRandomNumber() {
        int max = 6, min = 1;
        return getRandomNumberInRange(min, max);
    }

    public static int getRandomNumberForScaleAnimation() {
        return getRandomNumberInRange(500, 800);
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
