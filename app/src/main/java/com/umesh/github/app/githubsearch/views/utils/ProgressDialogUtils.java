package com.umesh.github.app.githubsearch.views.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.umesh.github.app.githubsearch.R;

/**
 * Created by Umesh on 18/8/16.
 */
public class ProgressDialogUtils {

    private static final boolean IS_CANCELABLE = false;
    private static final boolean IS_CANCELED_ON_TOUCH_OUTSIDE = false;

    public static ProgressDialog show(Context context, Boolean isCancelable, Boolean isCanceledOnTouchOutside) {

        ProgressDialog progressDialog = new android.app.ProgressDialog(context, R.style.CustomProgressDialogWithTransparentBackground);

        if (null != isCancelable)
            progressDialog.setCancelable(isCancelable);
        else
            progressDialog.setCancelable(IS_CANCELABLE);

        if (null != isCanceledOnTouchOutside)
            progressDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
        else
            progressDialog.setCanceledOnTouchOutside(IS_CANCELED_ON_TOUCH_OUTSIDE);

        progressDialog.setIndeterminate(true);
//        progressDialog.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.custom_progress_dialog));
        progressDialog.show();

        return progressDialog;
    }

    public static ProgressDialog show(Context context) {
        return show(context, null, null);
    }

    public static void stopProgressDialog(ProgressDialog progressDialog) {
        if (null != progressDialog && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
