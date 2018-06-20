package com.lt.hc.game;

import com.lt.hc.game.util.RandomNumberGeneratorUtil;

import java.util.Random;

public class Game {

    private GameListener gameListener;
    private GameConfig gameConfig;
    private Player player;
    private Player opponent;

    private Game() {

    }

    Game(GameConfig gameConfig, GameListener gameListener) {
        this.gameConfig = gameConfig;
        player = new Player();
        opponent = new Player();
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
        if (playerChoice == opponentChoice) {
            battingPlayer.out();
            if (battingPlayer.isAllOut()) {
                battingPlayer.setBattingOver(true);
                if (bowlingPlayer.isBattingOver()) {
                    if (battingPlayer.getScore() == bowlingPlayer.getScore()) {
                        gameListener.draw();
                    } else {
                        gameListener.allOut();
                    }
                }
            } else {
                gameListener.out();
            }
        } else {
            battingPlayer.score(run);
            gameListener.score(run);
            if (bowlingPlayer.isBattingOver() && battingPlayer.getScore() > bowlingPlayer.getScore()) {
                battingPlayer.setBattingOver(true);
                gameListener.win();
            }
        }
    }

    private void handlePlayerScore(int playerChoice, int opponentChoice) {
        if (playerChoice == opponentChoice) {


        } else {
            player.score(playerChoice);
            gameListener.playerScore(playerChoice);
            if (opponent.isBattingOver() && player.getScore() > opponent.getScore()) {
                gameListener.playerWin();
            }
        }
    }

    private int getOpponentChoice() {
        if (gameConfig.isAIOpponent()) {
            return new RandomNumberGeneratorUtil(gameConfig).getRandomNumber();
        }

        return 0;
    }


}
