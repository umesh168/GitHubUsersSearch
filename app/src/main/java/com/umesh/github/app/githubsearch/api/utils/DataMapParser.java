package com.umesh.github.app.githubsearch.api.utils;

import com.umesh.github.app.githubsearch.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataMapParser {

    /**
     * User related methods(User Details,Update User)
     **/

    public static User parseUser(Map<String, Object> userMap) {
        User user = new User();
        user.setLogin(userMap.get("login") + "");
        user.setAvatarUrl(userMap.get("avatar_url") + "");
        user.setScore(Float.parseFloat(userMap.get("score") + ""));
        return user;
    }

    public static List<User> parseUserList(List<Object> dataMap) {
        List<User> serviceCenterBranchList = new ArrayList<>();
        for (Object serviceCenter : dataMap) {
            serviceCenterBranchList.add(parseUser((Map<String, Object>) serviceCenter));
        }

        return serviceCenterBranchList;
    }
}
