package com.umesh.github.app.githubsearch.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/**
 * Created by sachin on 15-10-2015.
 */
public class TypefaceSpan extends MetricAffectingSpan {

    private Typeface mTypeface;

    public TypefaceSpan(Context context, String fontName) {
        mTypeface = TypefaceLoader.get(context,fontName);
    }

    @Override
    public void updateMeasureState(TextPaint textPaint) {
        textPaint.setTypeface(mTypeface);
        textPaint.setFlags(textPaint.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }

    @Override
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setTypeface(mTypeface);
        textPaint.setFlags(textPaint.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }
}
