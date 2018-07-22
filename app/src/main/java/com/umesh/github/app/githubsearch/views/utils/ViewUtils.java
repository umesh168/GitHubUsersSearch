package com.umesh.github.app.githubsearch.views.utils;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;


/**
 * Created by Umesh on 2/12/2016.
 */
public class ViewUtils {

    /**
     *
     * @param textView
     * @param colorId R.id.someColor
     */
    public static void setTextColor(TextView textView, int colorId) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textView.setTextColor(textView.getResources().getColor(colorId, null));
        } else {
            textView.setTextColor(textView.getResources().getColor(colorId));
        }
    }

    /**
     *
     * @param view
     * @param colorId R.id.someColor
     */
    public static void setBgColor(View view, int colorId) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setBackgroundColor(view.getResources().getColor(colorId, null));
        } else {
            view.setBackgroundColor(view.getResources().getColor(colorId));
        }
    }

    public static int getColor(int colorId, Context context) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getResources().getColor(colorId, null);
        } else {
            return context.getResources().getColor(colorId);
        }
    }

    public static void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
