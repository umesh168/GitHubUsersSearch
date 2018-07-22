package com.umesh.github.app.githubsearch.views.utils;

import android.view.animation.Interpolator;

/**
 * Created by dell on 9/8/17.
 */

public class BounceAnim implements Interpolator {
    private double mAmplitude = 1;
    private double mFrequency = 5;

    public BounceAnim (double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    @Override
    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }
}
