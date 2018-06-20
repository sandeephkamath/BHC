package com.lt.hc.game;

public abstract class GameListener {

    private Player player;
    private Player opponent;
    private GameConfig gameConfig;

    public GameListener() {

    }

    public GameListener(Player player, Player opponent) {
        this.player = player;
        this.opponent = opponent;
    }

    public void prepare(Player player, Player opponent, GameConfig gameConfig) {
        this.player = player;
        this.opponent = opponent;
        this.gameConfig = gameConfig;
    }

    public abstract void playerScore(int score, int playerChoice, int opponentChoice);

    public abstract void playerWicketFell(int totalWickets, int remainingWickets);

    public abstract void playerLose();

    public abstract void playerWin();

    public abstract void playerAllOut();

    public abstract void draw();

    public abstract void opponentScore(int score, int playerChoice, int opponentChoice);

    public abstract void opponentWicketFell(int totalWickets, int remainingWickets);

    public abstract void opponentLose();

    public abstract void opponentWin();

    public abstract void opponentAllOut();


    public void score(int score, int playerChoice, int opponentChoice) {
        if (player.isBatting()) {
            playerScore(score, playerChoice, opponentChoice);
        } else {
            opponentScore(score, playerChoice, opponentChoice);
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
            opponentLose();
        }
    }

    public void win() {
        if (player.isBatting()) {
            playerWin();
        } else {
            opponentWin();
        }
    }


    public void allOut() {
        if (player.isBatting()) {
            playerAllOut();
        } else {
            opponentAllOut();
        }
    }
}
