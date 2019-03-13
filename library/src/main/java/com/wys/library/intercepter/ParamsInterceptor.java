package com.wys.library.intercepter;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url();
        HttpUrl.Builder builder = url.newBuilder();
        builder.addQueryParameter("", "");
        return chain.proceed(request.newBuilder().url(url).build());
    }
}
