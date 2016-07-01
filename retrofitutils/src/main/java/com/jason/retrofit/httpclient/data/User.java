package com.jason.retrofit.httpclient.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hujd on 16-6-30.
 */
public class User{
    public long id;
    public String name;
    public String url;
    public String email;
    public String login;
    public String location;
    @SerializedName("avatar_url")
    public String avatarUrl;
}
