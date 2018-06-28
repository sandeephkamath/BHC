package com.lovoctech.bluetoothhandcricket.game.dependency;

import com.lovoctech.bluetoothhandcricket.HomeActivity;

import dagger.Component;

@Component(modules = {GameModule.class})
public interface GameComponent {

    void inject(HomeActivity homeActivity);

}
