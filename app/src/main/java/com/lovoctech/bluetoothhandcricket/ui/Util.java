package com.lovoctech.bluetoothhandcricket.ui;

import android.content.Context;
import android.util.DisplayMetrics;

public class Util {

    public static int  getDeviceWidth(Context context){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
       // int height = displayMetrics.heightPixels;
    }

}
