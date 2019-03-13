package com.wys.library.intercepter;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public abstract class BaseInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) {
        Request request1 = configLog(chain);
        Request request2 = configHeader(request1.newBuilder());
        Request request3 = configParams(request2);
        Request request4 = configOthers(request3);
        try {
            return chain.proceed(request4);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    abstract Request configLog(Chain request);

    abstract Request configHeader(Request.Builder builder);

    abstract Request configParams(Request request);

    abstract Request configOthers(Request request);

}
