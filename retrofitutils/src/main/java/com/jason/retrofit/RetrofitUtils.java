package com.jason.retrofit;

import com.jason.retrofit.httpclient.service.GithubHttpService;

public class RetrofitUtils {

    private static final GithubHttpService githubService = new GithubHttpService();

    /**
     * 创建Github服务
     * @return
     */
    public static GithubHttpService createGithubService() {
        return githubService;
    }
}
