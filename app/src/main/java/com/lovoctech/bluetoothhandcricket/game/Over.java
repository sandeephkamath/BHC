package com.lovoctech.bluetoothhandcricket.game;

import java.util.Locale;

public class Over {

    private final int over;
    private int ball;

    public Over(int over) {
        this.over = over;
    }

    public int getOver() {
        return over;
    }

    public void ballIncrement() {
        ball++;
    }

    public boolean isFinished() {
        return ball == over * 6;
    }

    public void reset() {
        ball = 0;
    }

    @Override
    public String toString() {
        int over = ball / 6;
        int ball = this.ball % 6;
        return String.format(Locale.ENGLISH, "%d.%d", over, ball);
    }
}
