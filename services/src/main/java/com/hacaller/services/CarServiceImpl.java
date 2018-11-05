package com.hacaller.services;

import com.hacaller.data.CarData;
import com.hacaller.data.CarService;
import com.hacaller.services.models.CarResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public class CarServiceImpl implements CarService {

    ServiceFactory serviceFactory;

    public CarServiceImpl(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public List<CarData> fetchCars() {
        List<CarData> cars = new ArrayList<>();
        try {
            List<CarResponse> list = serviceFactory.getCustomRetrofitService()
                    .getCars()
                    .execute()
                    .body();
            for (CarResponse carResponse: list){
                cars.add(CarObjectMapping.toCarModel(carResponse));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cars;
    }

}
