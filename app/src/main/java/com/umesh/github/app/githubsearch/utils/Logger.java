package com.umesh.github.app.githubsearch.utils;

import android.content.Context;
import android.util.Log;

import com.umesh.github.app.githubsearch.GitHubApp;
import com.umesh.github.app.githubsearch.R;

public class Logger {

    public static String TAG = "GitHub-App";

    private static Logger instance;
    private GitHubApp application;
    boolean debugEnabled;

    public static synchronized Logger init(GitHubApp application) {
        if(null == instance) {
            instance = new Logger(application);
        }
        return instance;
    }

    private Logger(GitHubApp application) {
        super();
        this.setApplication(application);
        debugEnabled = this.application.getResources().getBoolean(R.bool.isDebugEnabled);
    }

    public void debug(String msg) {
        if (debugEnabled && msg!=null) {
            Log.d(TAG, msg);
        }
    }

    public void debug(String msg, Throwable t) {
        if (debugEnabled && msg!=null) {
            Log.d(TAG, msg, t);
        }
    }

    public void debug(Throwable t) {
        if (debugEnabled) {
            Log.d(TAG, "Exception:", t);
        }
    }

    public void debug(String tag, String msg) {
        if (debugEnabled && msg!=null) {
            Log.d(tag, msg);
        }
    }

    public void warn(String msg) {
        if (debugEnabled && msg!=null) {
            Log.w(TAG, msg);
        }
    }

    public void info(String msg) {
        if (debugEnabled && msg!=null) {
            Log.i(TAG, msg);
        }
    }

    public void error(String msg) {
        if (debugEnabled && msg!=null) {
            Log.e(TAG, msg);
        }
    }

    public boolean isDebugEnabled() {
        return debugEnabled;
    }

    /**
     * @return the application
     */
    public Context getApplication() {
        return application;
    }

    /**
     * @param application the application to set
     */
    public void setApplication(GitHubApp application) {
        this.application = application;
    }

}
