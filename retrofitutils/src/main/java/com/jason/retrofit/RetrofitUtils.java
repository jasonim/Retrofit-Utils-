package com.jason.retrofit;

import com.jason.retrofit.httpclient.service.GithubHttpService;

public class RetrofitUtils {
    /**
     * 创建Github服务
     * @return
     */
    public static GithubHttpService createGithubService() {
        return GithubHttpService.Factory.create();
    }
}
