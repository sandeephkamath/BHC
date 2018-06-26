package com.lovoctech.bluetoothhandcricket.ui.custom;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lovoctech.bluetoothhandcricket.R;
import com.lovoctech.bluetoothhandcricket.databinding.RoundButtonBinding;
import com.lovoctech.bluetoothhandcricket.ui.Util;
import com.lovoctech.bluetoothhandcricket.ui.model.Choice;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RoundButton extends LinearLayout {

    @BindView(R.id.button)
    Button button;

    RoundButtonBinding binding;
    private Choice choice;

    public RoundButton(Context context) {
        super(context);
    }

    public RoundButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public RoundButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = RoundButtonBinding.inflate(inflater);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.round_button, this, true);
        ButterKnife.bind(this, binding.getRoot());
        setButtonWidth();
        //binding.setChoice(choice);
       /* if (inflater != null) {
            View view = inflater.inflate(R.layout.round_button, this);
            //ButterKnife.bind(view, this);
            setButtonWidth(context);
        }*/

    }

    private void setButtonWidth() {
        int size = Util.getDeviceWidth(getContext()) / 3;
         button.setLayoutParams(new LinearLayout.LayoutParams(size, size));
    }


    public void setChoice(Choice choice) {
        this.choice = choice;
        if (binding != null) {
            binding.setChoice(choice);
            binding.executePendingBindings();
        }
    }

}
