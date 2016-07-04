package com.jason.retrofit.httpclient.service;

import com.jason.retrofit.httpclient.data.User;
import com.jason.retrofit.httpclient.service.api.GithubAPIService;
import com.jason.retrofit.httpclient.ResponsCallback;

/**
 * Github相关请求
 * Created by hujd on 16-6-23.
 */
public class GithubHttpService extends HttpClient {
    public static final String GITHUB_API_BASE_URL = "https://api.github.com/";

    private final GithubAPIService mService;

    private GithubHttpService() {
        mService = createService(GithubAPIService.class, GITHUB_API_BASE_URL);
    }


    private static boolean prepare(ResponsCallback callback) {
        if(callback.prepare()) {
            callback.onStart();
            return true;
        }
        return false;
    }

    /**
     * 获取用户信息
     * @param userName 用户名
     * @param callback 回调
     */
    public void getUser(String userName, ResponsCallback<User> callback) {
        new UserSender().doSend(userName, callback);
    }

    /**
     * 发送数据基类
     * @param <T>
     * @param <S>
     */
    private abstract class BaseSender<T, S> {
        public void doSend(T data, ResponsCallback<S> callback) {
            if(prepare(callback)) {
                sendRequest(data, callback);
            }
        }

        protected abstract void sendRequest(T data, ResponsCallback<S> callback);
    }

    /**
     * 获取用户信息
     */
    private class UserSender extends BaseSender<String, User> {

        @Override
        protected void sendRequest(String data, ResponsCallback<User> callback) {
            mService.getUser(data).enqueue(callback);
        }
    }

    public static class Factory {
        private static final GithubHttpService githubSevice = new GithubHttpService();
        static public GithubHttpService create() {
            return githubSevice;
        }
    }
}
