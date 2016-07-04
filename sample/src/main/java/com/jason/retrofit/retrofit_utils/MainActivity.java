package com.jason.retrofit.retrofit_utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jason.retrofit.RetrofitUtils;
import com.jason.retrofit.httpclient.ResponsCallback;
import com.jason.retrofit.httpclient.data.User;
import com.jason.retrofit.httpclient.service.GithubHttpService;

public class MainActivity extends AppCompatActivity {

    private TextView userView;
    private GithubHttpService githubService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userView = (TextView) findViewById(R.id.text);
        initUserView();
    }

    private void initUserView() {
        githubService = RetrofitUtils.createGithubService();
        githubService.getUser("jasonim", new ResponsCallback<User>() {
            @Override
            public boolean prepare() {
                return true;
            }

            @Override
            public void onStart() {

            }

            @Override
            protected void onEnd() {

            }

            @Override
            protected void onSuccess(User user) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("email: ").append(user.email);
                stringBuilder.append("\nname: ").append(user.name);
                stringBuilder.append("\nurl: ").append(user.url);
                stringBuilder.append("\navatarUrl: ").append(user.avatarUrl);
                assert userView != null;
                userView.setText(stringBuilder.toString());
            }

            @Override
            protected void onFail(int code, String error) {
                assert userView != null;
                userView.setText(String.format("%d%s", code, error));
            }
        });
    }
}
