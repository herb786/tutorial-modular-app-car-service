package com.hacaller.business;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public class CarUseCases {

    CarRepository carRepository;

    public CarUseCases(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Callable<List<Car>> getAllCars() {
        return new Callable<List<Car>>() {
            @Override
            public List<Car> call() {
                System.out.println("BusinessLogicThread-->BlackBox: "+Thread.currentThread());
                return carRepository.getCarList();
            }
        };
    }

    public Callable<List<Car>>  getTopRatedCars() {
        return new Callable<List<Car>>() {
            @Override
            public List<Car> call() throws Exception {
                System.out.println("BusinessLogicThread-->BlackBox: "+Thread.currentThread());
                return carRepository.getTopRatedCars();
            }
        };
    }

    public Runnable setCarRating(final int id) {
        return new Runnable() {
            @Override
            public void run() {
                carRepository.setCarRating(id);
            }
        };
    }

}
