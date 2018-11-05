package com.hacaller.services;

import com.hacaller.services.models.CarResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public interface ServiceEndpoint {

    @GET("/api/cars")
    Call<List<CarResponse>> getCars();

}
