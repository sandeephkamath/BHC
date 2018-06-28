package com.lovoctech.bluetoothhandcricket.game;

import android.util.Log;

import com.lovoctech.bluetoothhandcricket.util.Constants;

public class Game {

    private GameListener gameListener;
    private GameConfig gameConfig;
    private Player player;
    private Player opponent;
    private Over overs;

    private Game() {

    }

    public Game(GameConfig gameConfig,
                GameListener gameListener,
                Player player,
                Player opponent,
                Over overs) {
        this.gameConfig = gameConfig;
        this.player = player;
        this.opponent = opponent;
        player.setBatting(true);//// TODO: 27/6/18 Implement toss logic
        player.setWickets(gameConfig.getWickets());
        opponent.setWickets(gameConfig.getWickets());
        this.gameListener = gameListener;
        this.overs = overs;
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
        overs.ballIncrement();
        gameListener.play(playerChoice, opponentChoice, overs.getOverString());

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
                    overs.reset();
                    battingPlayer.setBatting(false);
                    bowlingPlayer.setBatting(true);
                }
            } else {
                gameListener.out();
                checkForOverFinish(battingPlayer, bowlingPlayer);
            }
        } else {
            battingPlayer.score(run);
            gameListener.score(battingPlayer.getScore());
            checkForOverFinish(battingPlayer, bowlingPlayer);
            if (bowlingPlayer.isBattingOver() && battingPlayer.getScore() > bowlingPlayer.getScore()) {
                battingPlayer.setBattingOver(true);
                gameListener.win();
            }
        }
    }

    private void checkForOverFinish(Player battingPlayer, Player bowlingPlayer) {
        if (!overs.isFinished()) return;
        if (bowlingPlayer.isBattingOver()) {
            gameListener.declareBowlingPlayerWin();
        } else {
            battingPlayer.setBatting(false);
            battingPlayer.setBattingOver(true);
            bowlingPlayer.setBatting(true);
            gameListener.overFinish();
            overs.reset();
        }
    }

    private int getOpponentChoice() {
        if (gameConfig.isAIOpponent()) {
            return 1;//new RandomNumberGeneratorUtil(gameConfig).getRandomNumber();
        }

        return 0;
    }


}
