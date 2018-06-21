package com.lovoctech.bluetoothhandcricket.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lovoctech.bluetoothhandcricket.databinding.ChoiceHolderBinding;
import com.lovoctech.bluetoothhandcricket.ui.model.Choice;

/**
 * Created by Sandeep on 6/21/2018.
 */
class ChoiceViewHolder extends RecyclerView.ViewHolder {

    private final ChoiceHolderBinding choiceHolderBinding;
    private final ChoiceListener choiceListener;

    ChoiceViewHolder(ChoiceHolderBinding choiceHolderBinding, final ChoiceListener choiceListener) {
        super(choiceHolderBinding.getRoot());
        this.choiceHolderBinding = choiceHolderBinding;
        this.choiceListener = choiceListener;
    }

    void bind(final Choice choice) {
        choiceHolderBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiceListener.onChoice(choice);
            }
        });
        choiceHolderBinding.setChoice(choice);
        choiceHolderBinding.executePendingBindings();
    }
}
