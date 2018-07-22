package com.umesh.github.app.githubsearch.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.umesh.github.app.githubsearch.R;
import com.umesh.github.app.githubsearch.utils.TypefaceLoader;

/**
 * Created by Umesh on 8/14/2016.
 */
public class GitHubTextView extends AppCompatTextView{

    public GitHubTextView(Context context) {
        super(context);
        doInit(null, context);
    }

    public GitHubTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        doInit(attrs, context);
    }

    public GitHubTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        doInit(attrs, context);
    }

    private void doInit(AttributeSet attrs, Context context) {

        if(isInEditMode() || null == attrs) {
            return;
        }

        TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.GitHubTextView);
        int index = styledAttrs.getInt(R.styleable.GitHubTextView_customTypeFace, 1);
        String font;
        switch (index) {
            case 1:
                font = "Lato-Black.ttf";
                break;

            case 2:
                font = "Lato-Regular.ttf";
                break;

            case 3:
                font = "Lato-Bold.ttf";
                break;

            case 4:
                font = "Lato-Light.ttf";
                break;

            default:
                font = "Lato-Regular.ttf";
        }
        styledAttrs.recycle();
        Typeface typeface = TypefaceLoader.get(context, "fonts/" + font);
        setTypeface(typeface);
    }

}
