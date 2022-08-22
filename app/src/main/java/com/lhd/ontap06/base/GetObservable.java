package com.lhd.ontap06.base;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public abstract class GetObservable<T> implements Observer<T> {
    public abstract void onSuccess(T result);

    public abstract void onError(String msg);


    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        if (t == null) {
            onError("Không có dữ liệu!");
            return;
        }
        onSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onError(e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
