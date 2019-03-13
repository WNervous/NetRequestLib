package com.wys.library.intercepter;


import com.wys.library.download.ProgressListener;
import com.wys.library.download.ProgressResponseBody;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class DownloadInterceptor implements Interceptor {

    private ProgressListener progressListener;

    public DownloadInterceptor(ProgressListener listener) {
        progressListener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder().body(new ProgressResponseBody(response.body(), progressListener)).build();
    }
}
