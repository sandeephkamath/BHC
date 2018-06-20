package com.lt.hc.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lt.hc.databinding.ChoiceHolderBinding;
import com.lt.hc.ui.model.Choice;

import java.util.ArrayList;

/**
 * Created by Sandeep on 6/21/2018.
 */

public class ChoiceAdapter extends RecyclerView.Adapter<ChoiceViewHolder> {

    private final ChoiceListener choiceListener;
    private ArrayList<Choice> choices;

    public ChoiceAdapter(ArrayList<Choice> choices,ChoiceListener choiceListener) {
        this.choices = choices;
        this.choiceListener =choiceListener;
    }

    @NonNull
    @Override
    public ChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ChoiceHolderBinding choiceHolderBinding =
                ChoiceHolderBinding.inflate(layoutInflater, parent, false);
        return new ChoiceViewHolder(choiceHolderBinding,choiceListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ChoiceViewHolder holder, int position) {
        Choice item = choices.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return choices != null ? choices.size() : 0;
    }
}
