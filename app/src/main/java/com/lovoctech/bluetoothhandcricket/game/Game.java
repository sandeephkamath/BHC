package com.lovoctech.bluetoothhandcricket.game;

import android.util.Log;

import com.lovoctech.bluetoothhandcricket.ui.model.Choice;
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
        opponent.setBatting(true);//// TODO: 27/6/18 Implement toss logic

        this.gameListener = gameListener;
        this.overs = overs;
        gameListener.prepare(player, opponent, this.gameConfig);
    }

    public void choice(Choice playerChoice) {
        Choice opponentChoice = getOpponentChoice();
        if (player.isBatting()) {
            handleChoice(player, opponent, playerChoice, opponentChoice, playerChoice);
        } else {
            handleChoice(opponent, player, playerChoice, opponentChoice, opponentChoice);
        }
    }

    private void handleChoice(Player battingPlayer,
                              Player bowlingPlayer,
                              Choice playerChoice,
                              Choice opponentChoice,
                              Choice run) {

        Log.d(Constants.TAG, "TICK ");
        overs.ballIncrement();
        gameListener.play(playerChoice, opponentChoice, overs);

        if (playerChoice.equals(opponentChoice)) {
            battingPlayer.out();
            if (battingPlayer.isAllOut()) {
                battingPlayer.setBattingOver(true);
                if (bowlingPlayer.isBattingOver()) {
                    if (battingPlayer.isScoreEqual(bowlingPlayer)) {
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
            gameListener.score();
            checkForOverFinish(battingPlayer, bowlingPlayer);
            if (bowlingPlayer.isBattingOver() && battingPlayer.isScoreGreater(bowlingPlayer)) {
                battingPlayer.setBattingOver(true);
                gameListener.win();
            }
        }
        Log.d(Constants.TAG, toString());
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

    private Choice getOpponentChoice() {
        if (gameConfig.isAIOpponent()) {
            return new Choice(1);//new RandomNumberGeneratorUtil(gameConfig).getRandomNumber();
        }

        return new Choice(0);
    }

    @Override
    public String toString() {
        return "\nGame{" +
                ",\ngameConfig=\t" + gameConfig +
                ",\nPLAYER=\t" + player +
                ",\nOPPONENT=\t" + opponent +
                ",\novers=" + overs +
                "\n}";
    }
}
