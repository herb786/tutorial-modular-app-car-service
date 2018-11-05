package com.hacaller.business;

import java.util.List;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public interface CarRepository {

    List<Car> getCarList();
    void setCarRating(int id);
    List<Car> getTopRatedCars();

}
