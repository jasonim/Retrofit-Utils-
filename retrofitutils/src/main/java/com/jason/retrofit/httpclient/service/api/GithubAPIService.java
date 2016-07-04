package com.jason.retrofit.httpclient.service.api;

import com.jason.retrofit.httpclient.data.Repository;
import com.jason.retrofit.httpclient.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Github相关接口
 * Created by jason on 16-6-23.
 */
public interface GithubAPIService {
    @GET("users/{username}/repos")
    Call<List<Repository>> publicRepositories(@Path("username") String username);

    @GET("users/{username}/following")
    Call<List<User>> followingUser(@Path("username") String username);

    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

}
