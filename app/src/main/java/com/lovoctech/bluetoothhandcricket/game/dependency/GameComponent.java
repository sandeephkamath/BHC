package com.lovoctech.bluetoothhandcricket.game.dependency;

import com.lovoctech.bluetoothhandcricket.HomeActivity;
import com.lovoctech.bluetoothhandcricket.game.Game;
import com.lovoctech.bluetoothhandcricket.game.GameConfig;
import com.lovoctech.bluetoothhandcricket.game.Player;

import dagger.Component;

@Component(modules = {GameModule.class})
public interface GameComponent {

    void inject(HomeActivity homeActivity);

}
