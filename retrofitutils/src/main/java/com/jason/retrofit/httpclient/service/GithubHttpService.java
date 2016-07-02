package com.jason.retrofit.httpclient.service;

import com.jason.retrofit.httpclient.service.api.GithubAPIService;
import com.jason.retrofit.httpclient.ResponsCallback;

/**
 * Github相关请求
 * Created by hujd on 16-6-23.
 */
public class GithubHttpService extends HttpClient {
    public static final String GITHUB_API_BASE_URL = "https://api.github.com/";

    private final GithubAPIService mService;

    public GithubHttpService() {
        mService = createService(GithubAPIService.class, PAY_API_BASE_URL);
    }


    private static boolean prepare(ResponsCallback callback) {
        if(callback.prepare()) {
            callback.onStart();
            return true;
        }
        return false;
    }


    private abstract class BaseSender<T, S> {
        public void doSend(T data, ResponsCallback<S> callback) {
            if(prepare(callback)) {
                sendRequest(data, callback);
            }
        }

        protected abstract void sendRequest(T data, ResponsCallback<S> callback);
    }

}
