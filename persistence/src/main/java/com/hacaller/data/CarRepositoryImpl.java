package com.hacaller.data;

import com.hacaller.business.Car;
import com.hacaller.business.CarRepository;

import java.util.List;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public class CarRepositoryImpl implements CarRepository {

    CarService carService;
    CarDao carDao;

    public CarRepositoryImpl(CarService carService, CarDao carDao) {
        this.carService = carService;
        this.carDao = carDao;
    }

    @Override
    public List<Car> getCarList() {
        List<CarData> data = carDao.getCars();
        if (!data.isEmpty()) return CarDataMapper.toListBusinessCar(data);
        if (data.isEmpty()) {
            System.out.println("\u001B[46mCalling API service...");
            data = carService.fetchCars();
        }
        for (CarData carData: data){
            carDao.saveCar(carData);
        }
        return CarDataMapper.toListBusinessCar(data);
    }

    @Override
    public void updateCar(Car car) {
        carDao.updateCar(CarDataMapper.toCarData(car));
    }


}
