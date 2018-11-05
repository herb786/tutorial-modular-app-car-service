package com.hacaller.business;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public interface CaseObserver<T> {

    void onSuccess(T object);
    void onFailure(Throwable throwable);

}
