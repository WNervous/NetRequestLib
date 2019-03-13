package com.wys.library.obsevser;

import com.wys.library.bean.CommonResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class CommonObserver<T extends CommonResponse> implements Observer<T> {

    public abstract void onSuccess(T t);

    protected abstract void onFailed(Throwable throwable);

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        String message;
        if (t == null || t.getMessage() == null) {
            message = "Get data error";
            onFailed(new Throwable(message));
        } else if (t.success()) {
            onSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        onFailed(e);
    }

    @Override
    public void onComplete() {

    }
}
