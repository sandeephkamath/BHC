package com.lovoctech.bluetoothhandcricket.game;

import javax.inject.Inject;

public abstract class GameListener {

    private Player player;
    private Player opponent;
    private GameConfig gameConfig;

    public GameListener() {

    }

    @Inject
    public void GameListener(Player player, Player opponent, GameConfig gameConfig) {
        this.player = player;
        this.opponent = opponent;
        this.gameConfig = gameConfig;
    }

    public void prepare(Player player, Player opponent, GameConfig gameConfig) {
        this.player = player;
        this.opponent = opponent;
        this.gameConfig = gameConfig;
    }

    public abstract void playerScore(int score);

    public abstract void playerWicketFell(int totalWickets, int remainingWickets);

    public abstract void playerLose();

    public abstract void playerWin();

    public abstract void playerAllOut(int wickets);

    public abstract void playerOversFinish();

    public abstract void draw();

    public abstract void opponentScore(int score);

    public abstract void opponentWicketFell(int totalWickets, int remainingWickets);

    public abstract void opponentAllOut(int wickets);

    public abstract void choice(int playerChoice, int opponentChoice, String overs);

    public abstract void opponentOversFinish();


    public void play(int playerChoice, int opponentChoice,String overs) {
        choice(playerChoice, opponentChoice, overs);
    }

    public void score(int score) {
        if (player.isBatting()) {
            playerScore(score);
        } else {
            opponentScore(score);
        }
    }

    public void out() {
        if (player.isBatting()) {
            playerWicketFell(gameConfig.getWickets(), player.getWickets());
        } else {
            opponentWicketFell(gameConfig.getWickets(), opponent.getWickets());
        }
    }

    public void lose() {
        if (player.isBatting()) {
            playerLose();
        } else {
            playerWin();
        }
    }

    public void win() {
        if (player.isBatting()) {
            playerWin();
        } else {
            playerLose();
        }
    }


    public void allOut() {
        if (player.isBatting()) {
            playerAllOut(gameConfig.getWickets());
        } else {
            opponentAllOut(gameConfig.getWickets());
        }
    }


    public void overFinish() {
        if (player.isBatting()) {
            playerOversFinish();
        } else {
            opponentOversFinish();
        }
    }

    public void declareBowlingPlayerWin() {
        if (!player.isBatting()) {
            playerWin();
        } else {
            playerLose();
        }
    }
}
