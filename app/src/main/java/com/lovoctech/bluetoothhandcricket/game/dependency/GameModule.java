package com.lovoctech.bluetoothhandcricket.game.dependency;

import com.lovoctech.bluetoothhandcricket.game.Game;
import com.lovoctech.bluetoothhandcricket.game.GameConfig;
import com.lovoctech.bluetoothhandcricket.game.GameListener;
import com.lovoctech.bluetoothhandcricket.game.Player;

import dagger.Module;
import dagger.Provides;

@Module
public class GameModule {


    private final GameListener gameListener;

    public GameModule(GameListener gameListener) {
        this.gameListener = gameListener;
    }


    @Provides
    GameConfig providesGameConfig() {
        GameConfig gameConfig = new GameConfig();
        gameConfig.setWickets(2);
        gameConfig.setAIOpponent(true);
        return gameConfig;
    }

    @Provides
    Player providesPlayer() {
        return new Player();
    }

    @Provides
    Game providesGame(GameConfig gameConfig, Player player, Player opponent) {
        return new Game(gameConfig, gameListener, player, opponent);
    }

}
