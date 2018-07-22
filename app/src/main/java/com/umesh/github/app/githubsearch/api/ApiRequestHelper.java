package com.umesh.github.app.githubsearch.api;

import android.text.TextUtils;

import com.umesh.github.app.githubsearch.DataCache;
import com.umesh.github.app.githubsearch.GitHubApp;
import com.umesh.github.app.githubsearch.R;
import com.umesh.github.app.githubsearch.api.utils.DataMapParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Umesh
 */
public class ApiRequestHelper {

    public interface onRequestComplete {
        void onSuccess(Object object);

        void onFailure(ApiResponse apiResponse);
    }

    private static ApiRequestHelper instance;
    private GitHubApp application;
    private GitHubAppService gitHubAppService;

    private Map<Integer, Call> currentCalls = new HashMap<>();

    public static synchronized ApiRequestHelper init(GitHubApp application) {

        if (null == instance) {
            instance = new ApiRequestHelper();
            instance.setApplication(application);
            instance.createRestAdapter();
        }
        return instance;
    }

    public static synchronized void refreshInstance(GitHubApp gitHubApp) {
        if (gitHubApp != null) {
            instance = null;
            ApiRequestHelper.init(gitHubApp);
        }
    }

    private void cancelCall(int hashCode) {
        Call call = currentCalls.get(hashCode);
        if (null != call && !call.isExecuted() && !call.isCanceled()) {
            call.cancel();
        }
        currentCalls.remove(hashCode);
    }

    public void cancelAllCalls() {
        for (Integer hashCode : currentCalls.keySet()) {
            cancelCall(hashCode);
        }
    }

    /**
     * API Calls
     */
    public Integer getUser(final String searchKeyword, final int page, int resultPerPage,final onRequestComplete onRequestComplete) {
        String clientId = getApplication().getResources().getString(R.string.client_id);
        String clientSecret = getApplication().getResources().getString(R.string.client_secret);
        Call<ApiResponse> call = gitHubAppService.getUsers(searchKeyword, page,resultPerPage, clientId, clientSecret);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, retrofit2.Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    DataCache.setTotalRecords((int) apiResponse.getTotal_count());
                    onRequestComplete.onSuccess(DataMapParser.parseUserList(apiResponse.getItems()));
                } else {
                    onRequestComplete.onFailure(new ApiResponse().setErrors(ApiResponse.ApiError.GENERAL_ERROR));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                currentCalls.remove(call.hashCode());
                onRequestComplete.onFailure(new ApiResponse().setErrors(ApiResponse.ApiError.NETWORK_ERROR));
            }
        });

        currentCalls.put(call.hashCode(), call);
        return call.hashCode();
    }

    /*
     *End API Calls
     */

    /**
     * REST Adapter Configuration
     */
    private void createRestAdapter() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.addConverterFactory(JacksonConverterFactory.create());
        builder.baseUrl(getApplication().getResources().getString(R.string.server_url));
        builder.client(getHttpClient(getApplication().getResources().getString(R.string.client_id), getApplication().getResources().getString(R.string.client_secret)));
        Retrofit restAdapter = builder.build();
        gitHubAppService = restAdapter.create(GitHubAppService.class);
    }

    private OkHttpClient getHttpClient(final String clientId, final String clientSecret) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request.Builder request = chain.request().newBuilder().addHeader("Accept", "application/json");

                if (!TextUtils.isEmpty(clientSecret)) {
                    request.addHeader("client_id", clientId);
                    request.addHeader("client_secret", clientSecret);
                }
                request.method(chain.request().method(), chain.request().body());

                return chain.proceed(request.build());
            }
        });
        if (application.getResources().getBoolean(R.bool.isDebugEnabled)) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        return builder.build();
    }


    /**
     * End REST Adapter Configuration
     */

    public GitHubApp getApplication() {
        return application;
    }

    public void setApplication(GitHubApp application) {
        this.application = application;
    }

    public void setChariotAppService(GitHubAppService gitHubAppService) {
        this.gitHubAppService = gitHubAppService;
    }
}
