package com.wys.rxretrofitutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wys.library.bean.CommonResponse;
import com.wys.library.obsevser.CommonObserver;
import com.wys.rxretrofitutils.net.TestApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestApi.getInstance().test().getHomeBanner().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CommonObserver<CommonResponse<Object>>() {

            @Override
            public void onSuccess(CommonResponse<Object> objectCommonResponse) {
                Log.e("=======>", objectCommonResponse.toString());
            }

            @Override
            protected void onFailed(Throwable throwable) {
                Log.e("=======>", throwable.toString());
            }
        });
    }
}
