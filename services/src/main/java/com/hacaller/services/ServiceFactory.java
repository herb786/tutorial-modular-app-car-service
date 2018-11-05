package com.hacaller.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public class ServiceFactory {

    public ServiceEndpoint getCustomRetrofitService()  {
        return getCustomRetrofit().create(ServiceEndpoint.class);
    }

    public Retrofit getCustomRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://hacagusae.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
