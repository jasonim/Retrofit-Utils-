package com.jason.retrofit.httpclient.service;

import com.jason.retrofit.RetrofitUtils;
import com.jason.retrofit.httpclient.ResponsCallback;
import com.jason.retrofit.httpclient.data.User;
import com.jason.retrofit.retrofit_utils.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jason on 16-7-4.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 23)
public class GithubHttpServiceTest {
    private GithubHttpService githubService;

    @Before
    public void setup() {
        ShadowLog.stream = System.out;
        githubService = RetrofitUtils.createGithubService();
    }

    @Test
    public void testGetUser() throws Exception {

    }
}