package com.hacaller.data;

import com.hacaller.business.Car;
import com.hacaller.business.CarRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public class CarRepositoryImpl implements CarRepository {

    CarService carService;

    public CarRepositoryImpl(CarService carService) {
        this.carService = carService;
    }

    @Override
    public List<Car> getCarList() {
        List<Car> cars = new ArrayList<>();
        for (CarData carData: carService.fetchCars()){
            cars.add(carData.toBusinessCar());
        }
        return cars;
    }

    @Override
    public void setCarRating(int id) {

    }

    @Override
    public List<Car> getTopRatedCars() {
        return null;
    }

}
