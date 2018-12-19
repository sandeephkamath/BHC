package com.lovoctech.bluetoothhandcricket.game;

import com.lovoctech.bluetoothhandcricket.ui.model.Choice;

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

    public abstract void playerScore(Player player);

    public abstract void playerWicketFell(Player player);

    public abstract void playerLose();

    public abstract void playerWin();

    public abstract void playerAllOut(Player player);

    public abstract void playerOversFinish();

    public abstract void draw();

    public abstract void opponentScore(Player opponent);

    public abstract void opponentWicketFell(Player opponent);

    public abstract void opponentAllOut(Player opponent);

    public abstract void choice(Choice playerChoice, Choice opponentChoice, Over overs);

    public abstract void opponentOversFinish();


    public void play(Choice playerChoice, Choice opponentChoice, Over overs) {
        choice(playerChoice, opponentChoice, overs);
    }

    public void score() {
        if (player.isBatting()) {
            playerScore(player);
        } else {
            opponentScore(opponent);
        }
    }

    public void out() {
        if (player.isBatting()) {
            playerWicketFell(player);
        } else {
            opponentWicketFell(opponent);
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
            playerAllOut(player);
        } else {
            opponentAllOut(opponent);
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
