package com.umesh.github.app.githubsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umesh.github.app.githubsearch.adapter.UserListAdapter;
import com.umesh.github.app.githubsearch.api.ApiRequestHelper;
import com.umesh.github.app.githubsearch.api.ApiResponse;
import com.umesh.github.app.githubsearch.events.NextPageEvent;
import com.umesh.github.app.githubsearch.models.User;
import com.umesh.github.app.githubsearch.views.utils.SortChooser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Umesh.
 */

public class UserListActivity extends BaseActivity implements SortChooser.OnATOZSort, SortChooser.OnZTOASort, SortChooser.OnRankAsc, SortChooser.OnRankDesc{

    @BindView(R.id.search_keyword) TextView searchKeyword;
    RecyclerView lv_order;
    ImageView pencil;

    private int Lextender = 0;
    private int totalPages = 1;
    private int nextPage = 2;
    private List<User> orderList = new ArrayList<>();
    private List<User> showOrderList = new ArrayList<>();
    private UserListAdapter userListAdapter;
    private SortChooser sortChooser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ButterKnife.bind(UserListActivity.this);
        pencil = findViewById(R.id.pencile);
        lv_order = findViewById(R.id.lv_order);
        lv_order.setLayoutManager(new LinearLayoutManager(this));
        searchKeyword.setText(" 'Android'");
        initView();
    }

    public void initView() {
        pencil.setVisibility(View.GONE);
        getSearchList("Android");
    }

    @OnClick(R.id.tv_search)
    public void openSearch() {
        startActivity(new Intent(UserListActivity.this, SearchActivity.class));
    }
    public void getSearchList(String keyword) {
        getApp().getApiRequestHelper().getUser(keyword,1,100, new ApiRequestHelper.onRequestComplete() {
            @Override
            public void onSuccess(Object object) {
                orderList = (List<User>) object;
                refreshList();
//                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(ApiResponse apiResponse) {
//                progressBar.setVisibility(View.GONE);

            }
        });
    }

    public void fetchAllRecords(int totalRecords) {
        if (totalRecords > 1000)
            totalPages = 10;
        else {
            totalPages = (totalRecords / 100) + 1;
        }
        if (totalPages >= 2)
            getUsers("android", nextPage, 100);
    }

    public void getUsers(String searchKeyword, int pageNo, int perPage) {
        getApp().getApiRequestHelper().getUser(searchKeyword, pageNo, perPage, new ApiRequestHelper.onRequestComplete() {
            @Override
            public void onSuccess(Object object) {
                List<User> userRecords = (List<User>) object;
                orderList.addAll(userRecords);
                nextPage++;
                if (totalPages >= nextPage)
                    getUsers("android", nextPage, 100);
            }

            @Override
            public void onFailure(ApiResponse apiResponse) {

            }
        });
    }

    public void setAdapter(List<User> orders) {
        userListAdapter = new UserListAdapter(this, orders);
        lv_order.setAdapter(userListAdapter);

        userListAdapter.setOnBottomReachedListener(new UserListAdapter.onBottomReachedListener() {
            @Override
            public void onBottomReachedListener(int position) {
                Toast.makeText(UserListActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void callNextPage(){
        if (showOrderList.size() != orderList.size()) {
            if (orderList.size() > (Lextender + 25)) {
                for (int i = Lextender; i < Lextender + 25; i++) {
                    User order = orderList.get(i);
                    showOrderList.add(order);
                }
                Lextender = Lextender + 25;
            } else {
                for (int i = Lextender; i < orderList.size(); i++) {
                    User order = orderList.get(i);
                    showOrderList.add(order);
                }
                Lextender = orderList.size();
            }
            lv_order.post(new Runnable() {
                @Override
                public void run() {
                    userListAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @OnClick(R.id.sort)
    public void sort() {
        sortChooser = new SortChooser(UserListActivity.this, UserListActivity.this, UserListActivity.this, UserListActivity.this, UserListActivity.this);
        sortChooser.show();
    }

    public void refreshList() {
        if (orderList.size() > 25) {
            for (int i = Lextender ; i < Lextender + 25 ; i++){
                showOrderList.add(orderList.get(i));
            }
            Lextender = 24;
        } else showOrderList = orderList.subList(0,orderList.size());
        setAdapter(showOrderList);
        if (DataCache.getTotalRecords() > 100)
            fetchAllRecords(DataCache.getTotalRecords());
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(NextPageEvent appendListEvent) {
        if (appendListEvent.getAction().equals("Append")) {
            if (showOrderList.size() != orderList.size())
                callNextPage();
        } else if (appendListEvent.getAction().equals("Search")) {
            Lextender =0;
            orderList = new ArrayList<>();
            showOrderList = new ArrayList<>();
            userListAdapter.notifyDataSetChanged();
            searchKeyword.setText(" '" + appendListEvent.getNext()+"'");
            getSearchList(appendListEvent.getNext());
        }
        EventBus.getDefault().removeAllStickyEvents();
    }

   /* @Subscribe
    public void onSearchEvent(SearchKeywordEvent keywordEvent) {
        if (!CommonUtil.isEmpty(keywordEvent.getSearchKey())){
            orderList = new ArrayList<>();
            showOrderList = new ArrayList<>();
            userListAdapter.notifyDataSetChanged();
            getSearchList(keywordEvent.getSearchKey());
        }
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onATOZSelected() {
        Collections.sort(orderList,new Comparator<User>() {
            @Override
            public int compare(User a, User b) {
                return a.getLogin().compareTo(b.getLogin());
            }
        });

        Lextender = 0;
        showOrderList = new ArrayList<>();
        userListAdapter.notifyDataSetChanged();
        refreshList();
    }

    @Override
    public void onZTOASelected() {
        Collections.sort(orderList,new Comparator<User>() {
            @Override
            public int compare(User a, User b) {
                return b.getLogin().compareTo(a.getLogin());
            }
        });

        Lextender = 0;
        showOrderList = new ArrayList<>();
        userListAdapter.notifyDataSetChanged();
        refreshList();
    }

    @Override
    public void onAscSelected() {
        Collections.sort(orderList,new Comparator<User>() {
            @Override
            public int compare(User a, User b) {
                return Float.compare(a.getScore(), b.getScore());
            }
        });

        Lextender = 0;
        showOrderList = new ArrayList<>();
        userListAdapter.notifyDataSetChanged();
        refreshList();
    }

    @Override
    public void onDescSelected() {
        Collections.sort(orderList,new Comparator<User>() {
            @Override
            public int compare(User a, User b) {
                return Float.compare(b.getScore(), a.getScore());
            }
        });

        Lextender = 0;
        showOrderList = new ArrayList<>();
        userListAdapter.notifyDataSetChanged();
        refreshList();
    }
}
