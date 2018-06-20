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

    abstract void playerScore(int score);

    abstract void playerWicketFell(int totalWickets, int remainingWickets);

    abstract void playerLose();

    abstract void playerWin();

    abstract void draw();

    abstract void opponentScore();

    abstract void opponentWicketFell(int totalWickets, int remainingWickets);

    abstract void opponentLose();

    abstract void opponentWin();

    public void score(int score) {
        if (player.isBatting()) {
            playerScore(score);
        } else {
            opponentScore();
        }
    }

    public void out() {
        if (player.isBatting()) {
            playerWicketFell(gameConfig.getWickets(), player.getWickets());
        } else {
            opponentWicketFell(gameConfig.getWickets(), opponent.getWickets());
        }
    }

    public void allOut() {
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


}
