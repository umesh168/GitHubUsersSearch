package com.umesh.github.app.githubsearch;

import com.umesh.github.app.githubsearch.models.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Umesh.
 */
public class DataCache {

    private static List<User> users = new ArrayList<>();

    private static int totalRecords = 0;

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        DataCache.users = users;
    }

    public static int getTotalRecords() {
        return totalRecords;
    }

    public static void setTotalRecords(int totalRecords) {
        DataCache.totalRecords = totalRecords;
    }

    public static void clearDATACache() {
    }
}
