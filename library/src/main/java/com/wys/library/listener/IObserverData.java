package com.wys.library.listener;

import com.wys.library.bean.ComResponse;

public interface IObserverData<T> {

    void handleNext(ComResponse<T> t);
    void handleError(Throwable throwable);
}
