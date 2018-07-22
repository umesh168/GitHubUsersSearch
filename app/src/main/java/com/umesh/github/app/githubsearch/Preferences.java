package com.umesh.github.app.githubsearch;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.preference.PreferenceManager;

public class Preferences {

	private GitHubApp mGitHubApp;
    private static final String IS_FIRST_TIME_SETUP_DONE = "IS_FIRST_TIME_SETUP_DONE";
    private static final String IS_ACC_PERMISSION_GRANTED = "IS_ACC_PERMISSION_GRANTED";
    private static final String DATE = "DATE";
    private static final String SYSTEM_PROPERTY = "SYSTEM_PROPERTY";

    public Preferences(GitHubApp mGitHubApp) {
		this.mGitHubApp = mGitHubApp;
	}
	
	protected SharedPreferences getSharedPreferences(String key) {
		return PreferenceManager.getDefaultSharedPreferences(mGitHubApp);
	}

	private String getString(String key, String def) {
		SharedPreferences prefs = getSharedPreferences(key);
		String s = prefs.getString(key, def);
		return s;
	}
	private void setString(String key, String val) {
		SharedPreferences prefs = getSharedPreferences(key);
		Editor e = prefs.edit();
		e.putString(key, val);
		e.commit();
	}
	
	private boolean getBoolean(String key, boolean def) {
		SharedPreferences prefs = getSharedPreferences(key);
		boolean b = prefs.getBoolean(key, def);
		return b;
	}
	
	private void setBoolean(String key, boolean val) {
		SharedPreferences prefs = getSharedPreferences(key);
		Editor e = prefs.edit();
		e.putBoolean(key, val);
		e.commit();
	}

    private int getInt(String key, int def) {
        SharedPreferences prefs = getSharedPreferences(key);
        int i = Integer.parseInt(prefs.getString(key, Integer.toString(def)));
        return i;
    }

    private void setInt(String key, int val) {
        SharedPreferences prefs = getSharedPreferences(key);
        Editor e = prefs.edit();
        e.putString(key, Integer.toString(val));
        e.commit();
    }

    private float getFloat(String key, float def) {
        SharedPreferences prefs = getSharedPreferences(key);
        float f = Float.parseFloat(prefs.getString(key, Float.toString(def)));
        return f;
    }

    private void setFloat(String key, float val) {
        SharedPreferences prefs = getSharedPreferences(key);
        Editor e = prefs.edit();
        e.putString(key, Float.toString(val));
        e.commit();
    }

    private long getLong(String key, long def) {
        SharedPreferences prefs = getSharedPreferences(key);
        long l = Long.parseLong(prefs.getString(key, Long.toString(def)));
        return l;
    }

    private void setLong(String key, long val) {
        SharedPreferences prefs = getSharedPreferences(key);
        Editor e = prefs.edit();
        e.putString(key, Long.toString(val));
        e.commit();
    }

    private Uri getUri(String key, Uri def) {
        SharedPreferences prefs = getSharedPreferences(key);
        Uri uri = Uri.parse(prefs.getString(key,def.toString()));
        return uri;
    }

    private void setUri(String key, Uri val) {
        SharedPreferences prefs = getSharedPreferences(key);
        Editor e = prefs.edit();
        e.putString(key,val.toString());
        e.commit();
    }

    public boolean getIsFirstTimeSetupDone() {
        return getBoolean(IS_FIRST_TIME_SETUP_DONE, false);
    }

    public void setIsFirstTimeSetupDone(boolean isDone) {
        setBoolean(IS_FIRST_TIME_SETUP_DONE, isDone);
    }

    public boolean getAccountPermission() {
        return getBoolean(IS_ACC_PERMISSION_GRANTED, false);
    }

    public void setAccountPermission(boolean isDone) {
        setBoolean(IS_ACC_PERMISSION_GRANTED, isDone);
    }

    public synchronized void setCurrentTime(long timeInMillis) {
        setLong(DATE, timeInMillis);
    }

    public synchronized long getCurrentTime() {
        return getLong(DATE, 0);
    }

}
