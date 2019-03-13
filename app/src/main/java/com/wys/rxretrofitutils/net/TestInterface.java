package com.wys.rxretrofitutils.net;

import com.wys.library.bean.CommonResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TestInterface {

    @GET("banner/json")
    Observable<CommonResponse<Object>> getHomeBanner();
}
