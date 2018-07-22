package com.umesh.github.app.githubsearch;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SAI on 22/07/2018.
 */

public class UserDetailActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_user_details);
    }
}
