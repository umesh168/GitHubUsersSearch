package com.umesh.github.app.githubsearch;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.umesh.github.app.githubsearch.utils.AppConstants;
import com.umesh.github.app.githubsearch.views.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Umesh on 8/15/2016.
 */
public class BaseActivity extends AppCompatActivity {

    private GitHubApp app;
    private boolean isAlive = true;

    public interface PermissionCallback {
        /**
         * Called when all the permissions are granted
         */
        void onAllPermissionGranted();

        /**
         * Called when any one of the permission is denied
         * @param deniedPermissions Denied Permissions
         */
        void onPermissionsDenied(String[] deniedPermissions);
    }

    private PermissionCallback permissionCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (GitHubApp) getApplication();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(null != toolbar) {
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
//            toolbar.setNavigationIcon(R.drawable.ic_back_arrow_white);
            setTitle(getTitle());
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        TextView titleTv = (TextView) findViewById(R.id.toolbar_title);
        if(null != titleTv)
            titleTv.setText(title);
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
       setTitle(getString(titleId));
    }

    protected void requestForPermissions(@NonNull String[] permissions, String explaination, @NonNull PermissionCallback callback) {

        final List<String> requiredPermissions = new ArrayList<>();
        for(String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                requiredPermissions.add(permission);
            }
        }
        if(requiredPermissions.isEmpty()) {
            callback.onAllPermissionGranted();
            return;
        }
        permissionCallback = callback;
        boolean showExplanation = false;
        for(String permission : requiredPermissions) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showExplanation = true;
                break;
            }
        }
        if (showExplanation) {
            DialogUtils.show(this, explaination, getString(R.string.permission_required), getString(R.string.ok), false, false, new DialogUtils.ActionListner() {
                @Override
                public void onPositiveAction() {
                    ActivityCompat.requestPermissions(BaseActivity.this, requiredPermissions.toArray(new String[requiredPermissions.size()]), AppConstants.REQUEST_PERMISSION);
                }

                @Override
                public void onNegativeAction() {
                    //N/A
                }
            });
        } else {
            ActivityCompat.requestPermissions(this, requiredPermissions.toArray(new String[requiredPermissions.size()]), AppConstants.REQUEST_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<String> deniedPermissions = new ArrayList<>();
        for(int i = 0; i < grantResults.length; i++) {
            int permissionStatus = grantResults[i];
            if(permissionStatus != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permissions[i]);
            }
        }
        if(deniedPermissions.isEmpty()) {
            permissionCallback.onAllPermissionGranted();
            return;
        }
        permissionCallback.onPermissionsDenied(deniedPermissions.toArray(new String[deniedPermissions.size()]));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            Intent intent = NavUtils.getParentActivityIntent(this);
            if(null != intent) {
                NavUtils.navigateUpTo(this, intent);
            } else {
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        isAlive = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public GitHubApp getApp() {
        return app;
    }
}
