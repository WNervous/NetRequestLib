package com.wys.library;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApi {

    private OkHttpClient mOkHttpClient;
    private Retrofit     mRetorfit;

    public BaseApi(Builder builder) {

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.connectTimeout(builder.mConnectTimeout, TimeUnit.SECONDS).readTimeout(builder.mReadTimeout, TimeUnit.SECONDS).writeTimeout(builder.mWriteTimeout, TimeUnit.SECONDS).retryOnConnectionFailure(builder.mRetryOnConnectionFailure);

        if (builder.mLogInterceptor != null && !builder.mIsRelease) {
            clientBuilder.addInterceptor(builder.mLogInterceptor);
        }
        if (builder.mNetworkInterceptor != null && !builder.mIsRelease) {
            clientBuilder.addInterceptor(builder.mNetworkInterceptor);
        }
        if (builder.mParamsInterceptor != null) {
            clientBuilder.addInterceptor(builder.mParamsInterceptor);
        }
        if (builder.mHeaderInterceptor != null) {
            clientBuilder.addInterceptor(builder.mHeaderInterceptor);
        }
        mOkHttpClient = clientBuilder.build();

        mRetorfit = new Retrofit.Builder().baseUrl(builder.mIsRelease ? builder.mBaseUrl : builder.mBaseTestUrl).client(mOkHttpClient).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public <T> T create(Class<T> t) {
        return mRetorfit.create(t);
    }

   public static class Builder {

        public static final long DEFAULT_TIME = 15;

        private Interceptor mNetworkInterceptor;
        private Interceptor mHeaderInterceptor;
        private Interceptor mParamsInterceptor;
        private Interceptor mLogInterceptor;

        private String mBaseUrl;
        private String mBaseTestUrl;

        private long mReadTimeout    = DEFAULT_TIME;
        private long mConnectTimeout = DEFAULT_TIME;
        private long mWriteTimeout   = DEFAULT_TIME;

        private boolean mRetryOnConnectionFailure;
        private boolean mCache;
        private boolean mIsRelease;


        public Builder setNetworkInterceptor(Interceptor mNetworkInterceptor) {
            this.mNetworkInterceptor = mNetworkInterceptor;
            return this;
        }

        public Builder setHeaderInterceptor(Interceptor mHeaderInterceptor) {
            this.mHeaderInterceptor = mHeaderInterceptor;
            return this;
        }

        public Builder setParamsInterceptor(Interceptor mParamsInterceptor) {
            this.mParamsInterceptor = mParamsInterceptor;
            return this;
        }

        public Builder setLogInterceptor(Interceptor mLogInterceptor) {
            this.mLogInterceptor = mLogInterceptor;
            return this;
        }

        public Builder setBaseUrl(String mBaseUrl) {
            this.mBaseUrl = mBaseUrl;
            return this;
        }

        public Builder setBaseTestUrl(String mBaseTestUrl) {
            this.mBaseTestUrl = mBaseTestUrl;
            return this;
        }

        public Builder setReadTimeout(long mReadTimeout) {
            this.mReadTimeout = mReadTimeout;
            return this;
        }

        public Builder setConnectTimeout(long mConnectTimeout) {
            this.mConnectTimeout = mConnectTimeout;
            return this;
        }

        public Builder setWriteTimeout(long mWriteTimeout) {
            this.mWriteTimeout = mWriteTimeout;
            return this;
        }

        public Builder setRetryOnConnectionFailure(boolean mRetryOnConnectionFailure) {
            this.mRetryOnConnectionFailure = mRetryOnConnectionFailure;
            return this;
        }

        public Builder setCache(boolean mCache) {
            this.mCache = mCache;
            return this;
        }

        public Builder setIsRelease(boolean mIsRelease) {
            this.mIsRelease = mIsRelease;
            return this;
        }

        public BaseApi build() {
            return new BaseApi(this);
        }

    }
}