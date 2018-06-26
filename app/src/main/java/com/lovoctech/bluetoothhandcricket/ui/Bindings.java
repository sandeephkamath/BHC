package com.lovoctech.bluetoothhandcricket.ui;

import android.databinding.BindingAdapter;

import com.lovoctech.bluetoothhandcricket.ui.custom.RoundButton;
import com.lovoctech.bluetoothhandcricket.ui.model.Choice;

public class Bindings {

    @BindingAdapter("choice")
    public static void setChoice(RoundButton roundButton, Choice choice) {
        roundButton.setChoice(choice);
    }
}
