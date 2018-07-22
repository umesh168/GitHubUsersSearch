package com.umesh.github.app.githubsearch;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.SearchEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.umesh.github.app.githubsearch.events.NextPageEvent;
import com.umesh.github.app.githubsearch.events.SearchKeywordEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;

/**
 * Created by Umesh on 22/07/2018.
 */

public class SearchActivity extends BaseActivity {

    @BindView(R.id.et_search)
    EditText search;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(SearchActivity.this);
    }

    @OnEditorAction(R.id.et_search)
    public boolean onSearch(TextView tv, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            EventBus.getDefault().postSticky(new NextPageEvent((search.getText() + ""), 0, "Search") );
            Toast.makeText(this, search.getText() + "", Toast.LENGTH_SHORT).show();
            finish();
        }
        return false;
    }
}
