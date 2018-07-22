package com.umesh.github.app.githubsearch.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Umesh on 1/20/2016.
 */
public interface GitHubAppService {

    @GET("/search/users")
    Call<ApiResponse> getUsers(@Query("q") String searchItem, @Query("page") int pageNo, @Query("per_page") int perPageResults, @Query("client_id") String clientId, @Query("client_secret") String clientSecret);

    @GET("users/")
    Call<ApiResponse> userDetail(@Query("userName") String userName);
}
