package com.jason.retrofit.httpclient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hujd on 16-6-24.
 */
public abstract class ResponsCallback<T> implements Callback<T> {

    /**
     * 如进行网络处理等
     */
    public abstract boolean prepare();
    /**
     * 请求开始时被调用
     */
    public abstract void onStart();

    /**
     * 请求结束被调用
     */
    protected abstract void onEnd();

    /**
     * 请求响应回调
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        try {
            if (response.isSuccessful()) {
                T data = response.body();
                if (data != null) {
                    onSuccess(data);
                } else {
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        try {
                            onFail(-1, responseBody.string());
                        } catch (IOException e) {
                            onFail(-1, e.getMessage());
                            e.printStackTrace();
                        }
                    } else {
                        onFail(response.code(), "responseBody is null");
                    }
                }
            } else {
                onFail(response.code(), "response is failure");
            }
        } finally {
           onEnd();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFail(-1, t.getMessage());
    }

    /**
     * 请求成功将数据进行处理
     *
     * @param response
     */
    protected abstract void onSuccess(T response);

    /**
     * 请求失败，将错误信息进行处理
     *
     * @param code
     * @param error
     */
    protected abstract void onFail(int code, String error);
}
