package com.lovoctech.bluetoothhandcricket.game;

import android.util.Log;

import com.lovoctech.bluetoothhandcricket.util.Constants;

public class Game {

    private GameListener gameListener;
    private GameConfig gameConfig;
    private Player player;
    private Player opponent;

    private Game() {

    }

    public Game(GameConfig gameConfig, GameListener gameListener) {
        this.gameConfig = gameConfig;
        player = new Player();
        opponent = new Player();

        player.setBatting(true);//////////////////
        player.setWickets(gameConfig.getWickets());
        opponent.setWickets(gameConfig.getWickets());


        this.gameListener = gameListener;
        gameListener.prepare(player, opponent, this.gameConfig);
    }

    public void choice(int playerChoice) {
        int opponentChoice = getOpponentChoice();
        if (player.isBatting()) {
            handleChoice(player, opponent, playerChoice, opponentChoice, playerChoice);
        } else {
            handleChoice(opponent, player, playerChoice, opponentChoice, opponentChoice);
        }
    }

    private void handleChoice(Player battingPlayer,
                              Player bowlingPlayer,
                              int playerChoice,
                              int opponentChoice,
                              int run) {

        Log.d(Constants.TAG, "TICK ");

        gameListener.choice(playerChoice, opponentChoice);
        if (playerChoice == opponentChoice) {
            battingPlayer.out();
            if (battingPlayer.isAllOut()) {
                battingPlayer.setBattingOver(true);
                if (bowlingPlayer.isBattingOver()) {
                    if (battingPlayer.getScore() == bowlingPlayer.getScore()) {
                        gameListener.draw();
                    } else {
                        gameListener.lose();
                    }
                } else {
                    gameListener.allOut();
                    battingPlayer.setBatting(false);
                    bowlingPlayer.setBatting(true);
                }
            } else {
                gameListener.out();
            }
        } else {
            battingPlayer.score(run);
            gameListener.score(battingPlayer.getScore());
            if (bowlingPlayer.isBattingOver() && battingPlayer.getScore() > bowlingPlayer.getScore()) {
                battingPlayer.setBattingOver(true);
                gameListener.win();
            }
        }
    }

    private int getOpponentChoice() {
        if (gameConfig.isAIOpponent()) {
            return 1;//new RandomNumberGeneratorUtil(gameConfig).getRandomNumber();
        }

        return 0;
    }


}
