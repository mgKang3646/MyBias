package com.example.actorsearchapplication.viewutil;

import android.graphics.Color;
import android.graphics.ColorFilter;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;

public class LottieUtil {

    public static void setColor(LottieAnimationView lottieAnimationView, int color) {
        SimpleColorFilter filter = new SimpleColorFilter(color);
        KeyPath keyPath = new KeyPath("**");
        LottieValueCallback<ColorFilter> callback = new LottieValueCallback<ColorFilter>(filter);
        lottieAnimationView.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback);
    }
}
