package com.umesh.github.app.githubsearch;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.umesh.github.app.githubsearch.api.ApiRequestHelper;
import com.umesh.github.app.githubsearch.utils.DataHelper;
import com.umesh.github.app.githubsearch.utils.Logger;

/**
 * Created by Umesh on 7/05/2017.
 */
@SuppressWarnings("LossyEncoding")
public class GitHubApp extends Application {

    private Logger logger;
    private Preferences preferences;
    private ApiRequestHelper apiRequestHelper;
    private DataHelper dataHelper;
    private boolean isNetworkAvailable;
    private boolean isNonLoginUserAccess = false;
    private boolean isUseServerURLFromConstant = false;
    private int versionCode;
    private String versionName;

    @Override
    public void onCreate() {
        super.onCreate();
        doInit();
    }

    public GitHubApp() {
        super();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    private void doInit() {

        this.logger = Logger.init(this);
        this.preferences = new Preferences(this);
        this.apiRequestHelper = ApiRequestHelper.init(this);
        this.dataHelper = new DataHelper(this);
        //this.dbUtils = new DBUtils(this);

        checkNetworkConnection();
        DataHelper.createDataDirIfNotExists();
        //startFirebaseService();


        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionCode = pInfo.versionCode;
            versionName = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    public synchronized ApiRequestHelper getApiRequestHelper() {
        return apiRequestHelper;
    }

    public synchronized Logger getLogger() {
        return logger;
    }

    public synchronized Preferences getPreferences() {
        return preferences;
    }

    public boolean getIsNetworkAvailable() {
        return isNetworkAvailable;
    }

    public void setIsNetworkAvailable(boolean isNetworkAvailable) {
        this.isNetworkAvailable = isNetworkAvailable;
    }

    private void checkNetworkConnection() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        isNetworkAvailable = activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public String getBaseServerURL() {
        String url = "";
        if (isUseServerURLFromConstant) {
            url = getResources().getString(R.string.server_url);
        } else {
            url = getString(R.string.server_url);
        }
        return url;
    }
}
