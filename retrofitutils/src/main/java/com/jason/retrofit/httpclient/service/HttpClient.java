package com.jason.retrofit.httpclient.service;

import com.jason.retrofit.httpclient.okhttp.OkHttpManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Http Client 基类
 * Created by hujd on 16-6-23.
 */
public abstract class HttpClient {

    public static final String DEFAULT_API_BASE_URL = "https://api.github.com/";


    private static final Retrofit.Builder builder =
            new Retrofit.Builder().
            addConverterFactory(GsonConverterFactory.create());

    /**
     * 创建制定api服务
     * @param serviceClass
     * @param baseUrl
     * @param <T>
     * @return
     */
    protected <T> T createService(Class<T> serviceClass, String baseUrl) {
        setApiBaseUrl(baseUrl);
        OkHttpClient client = OkHttpManager.getOkHttpClient(false);
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
    /**
     * 设置base url
     */
    private void setApiBaseUrl(String baseUrl) {
        String apiBaseUrl = baseUrl == null ? DEFAULT_API_BASE_URL : baseUrl;
        builder.baseUrl(apiBaseUrl);
    }
}
