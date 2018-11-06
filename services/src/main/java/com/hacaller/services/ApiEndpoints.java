package com.hacaller.services;

import com.hacaller.services.models.CarResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Herbert Caller on 06/11/2018.
 */
public interface ApiEndpoints {

    @GET("/api/cars")
    Call<List<CarResponse>> getCars();

}
