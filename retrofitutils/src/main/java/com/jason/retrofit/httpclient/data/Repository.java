package com.jason.retrofit.httpclient.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hujd on 16-6-30.
 */
public class Repository{
    public long id;
    public String name;
    public String description;
    public int forks;
    public int watchers;
    @SerializedName("stargazers_count")
    public int stars;
    public String language;
    public String homepage;
    public User owner;
    public boolean fork;

    public Repository() {
    }
}