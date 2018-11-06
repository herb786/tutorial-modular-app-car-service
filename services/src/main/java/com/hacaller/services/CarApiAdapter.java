package com.hacaller.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public class CarApiAdapter implements ApiAdapter {

    @Override
    public ApiEndpoints connect() {
        return getCustomRetrofit().create(ApiEndpoints.class);
    }

    @Override
    public Retrofit.Builder setLogging(boolean hasLog) {
        return null;
    }


    public static Retrofit getCustomRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://hacagusae.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
