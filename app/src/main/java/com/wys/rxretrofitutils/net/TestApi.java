package com.wys.rxretrofitutils.net;

import com.wys.library.BaseApi;

import okhttp3.logging.HttpLoggingInterceptor;

public class TestApi {


    public String mBaseUrl = "http://www.wanandroid.com/";
    public String mBaseDebugUrl = "http://www.wanandroid.com/";

    private BaseApi mBaseApi;
    private BaseApi mFileApi;

    public static TestApi getInstance(){
        return Holder.INSTANCE;
    }

    public TestApi() {
        BaseApi.Builder mBuilder = new BaseApi.Builder();
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mBaseApi = mBuilder.setBaseTestUrl(mBaseUrl).setBaseTestUrl(mBaseDebugUrl).setLogInterceptor(loggingInterceptor).setCache(true).build();
    }

    public static class Holder {
       public static TestApi INSTANCE=new TestApi();
    }

    public TestInterface test() {
        return mBaseApi.create(TestInterface.class);
    }

}
