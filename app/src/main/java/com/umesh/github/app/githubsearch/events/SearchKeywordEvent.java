package com.umesh.github.app.githubsearch.events;

import java.io.Serializable;

/**
 * Created by dell on 19/7/18.
 */

public class SearchKeywordEvent implements Serializable {

    private String searchKey;

    public SearchKeywordEvent(String search) {
        this.searchKey = search;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}
