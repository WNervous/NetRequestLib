package com.wys.library.intercepter;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public class DefaultBaseInterceptor extends BaseInterceptor {


    @Override
    Request configLog(Chain chain) {
        Request request = chain.request();
        long t1 = System.nanoTime();
        Log.e("okhttp", String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
        Response response;
        try {
            response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.e("okhttp", String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    @Override
    Request configHeader(Request.Builder builder) {
        builder.addHeader("User-Agent", "Android");
        return builder.build();
    }

    @Override
    Request configParams(Request request) {
        HttpUrl httpUrl = request.url();
        httpUrl.newBuilder().addQueryParameter("platform", "android").addQueryParameter("version", "1.0.0");
        return request.newBuilder().url(httpUrl).build();
    }

    @Override
    Request configOthers(Request request) {
        return request;
    }
}
