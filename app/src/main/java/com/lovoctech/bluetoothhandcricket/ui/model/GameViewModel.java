package com.lovoctech.bluetoothhandcricket.ui.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class GameViewModel extends ViewModel {

    private MutableLiveData<GameUIModel> gameUIModel = new MutableLiveData<>();

    public LiveData<GameUIModel> getGameUIModel() {
        return gameUIModel;
    }

    public void setValue(GameUIModel gameUIModel) {
        this.gameUIModel.setValue(gameUIModel);
    }


}
