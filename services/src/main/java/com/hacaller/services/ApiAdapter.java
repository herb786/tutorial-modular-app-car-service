package com.hacaller.services;

import retrofit2.Retrofit;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public interface ApiAdapter {

    ApiEndpoints connect();

    Retrofit.Builder setLogging(boolean hasLog);

}
