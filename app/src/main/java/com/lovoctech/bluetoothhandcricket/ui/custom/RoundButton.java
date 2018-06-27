package com.lovoctech.bluetoothhandcricket.ui.custom;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lovoctech.bluetoothhandcricket.R;

import com.lovoctech.bluetoothhandcricket.databinding.RoundButtonBinding;
import com.lovoctech.bluetoothhandcricket.util.RandomNumberGeneratorUtil;
import com.lovoctech.bluetoothhandcricket.ui.ChoiceListener;
import com.lovoctech.bluetoothhandcricket.ui.Util;
import com.lovoctech.bluetoothhandcricket.ui.model.Choice;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RoundButton extends LinearLayout {

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.root_layout)
    LinearLayout rootLayout;

    private ChoiceListener listener;

    @OnClick(R.id.button)
    public void onClick() {
        if (listener != null) {
            listener.onChoice(choice);
        }
        shakeAnimate();
    }

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
        popupAnimate();
    }

    private void setButtonWidth() {
        int size = Util.getDeviceWidth(getContext()) / 4;
        int padding = getResources().getDimensionPixelOffset(R.dimen.size_10) * 2;
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(size, size));
        size = size - padding;
        button.setLayoutParams(new LinearLayout.LayoutParams(size, size));
    }


    public void setChoice(Choice choice) {
        this.choice = choice;
        if (binding != null) {
            binding.setChoice(choice);
            binding.executePendingBindings();
        }
    }

    public void setListener(ChoiceListener listener) {
        this.listener = listener;
    }

    public void popupAnimate() {
        Animation anim = new ScaleAnimation(
                0f, 1f, // Start and end values for the X axis scaling
                0f, 1f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(RandomNumberGeneratorUtil.getRandomNumberForScaleAnimation());
        button.startAnimation(anim);
    }

    public void shakeAnimate() {
        ObjectAnimator
                .ofFloat(button, "translationX", 0, 25, -25, 25, -25, 15, -15, 6, -6, 0)
                .setDuration(1000)
                .start();
    }

}
