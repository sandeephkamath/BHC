package com.lovoctech.bluetoothhandcricket.game.dependency;

import android.support.v4.app.FragmentActivity;

import com.lovoctech.bluetoothhandcricket.game.Game;
import com.lovoctech.bluetoothhandcricket.game.GameConfig;
import com.lovoctech.bluetoothhandcricket.game.GameListener;
import com.lovoctech.bluetoothhandcricket.game.Over;
import com.lovoctech.bluetoothhandcricket.game.Player;
import com.lovoctech.bluetoothhandcricket.ui.model.Choice;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class GameModule {


    private GameListener gameListener;
    private FragmentActivity activity;


    public GameModule(GameListener gameListener,FragmentActivity activity) {
        this.gameListener = gameListener;
        this.activity = activity;
    }


    @Provides
    GameConfig providesGameConfig() {
        GameConfig gameConfig = new GameConfig();
        gameConfig.setWickets(2);
        gameConfig.setAIOpponent(true);
        gameConfig.setOvers(2);
        return gameConfig;
    }

    @Provides
    Player providesPlayer() {
        return new Player();
    }

    @Provides
    Game providesGame(GameConfig gameConfig, Player player, Player opponent, Over overs) {
        return new Game(gameConfig, gameListener, player, opponent, overs);
    }

    @Provides
    Over providesOvers(GameConfig gameConfig) {
        return new Over(gameConfig.getOvers());
    }

    @Provides
    ArrayList<Choice> providesChoices() {
        ArrayList<Choice> choices = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            Choice choice = new Choice();
            choice.setValue(i);
            choices.add(choice);
        }
        return choices;
    }


}
