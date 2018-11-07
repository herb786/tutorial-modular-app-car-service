package com.hacaller.data;

import java.util.List;

/**
 * Created by Herbert Caller on 06/11/2018.
 */
public interface CarDao {

    void saveCar(CarData carData);
    List<CarData> getCars();
    void updateCar(CarData carData);
    void deleteCars();

}
