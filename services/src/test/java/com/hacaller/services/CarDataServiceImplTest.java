package com.hacaller.services;

import com.hacaller.data.CarData;

import org.junit.Before;
import org.junit.Test;

import java.util.List;


/**
 * Created by Herbert Caller on 04/11/2018.
 */
public class CarDataServiceImplTest {

    CarApiAdapter carApiAdapter;
    CarServiceImpl carServiceImpl;

    @Before
    public void setup() {
        carApiAdapter = new CarApiAdapter();
        carServiceImpl = new CarServiceImpl(carApiAdapter);
    }

    @Test
    public void fetchCarsTest() {
        System.out.print("Testing car endpoint...");
        List<CarData> cars = carServiceImpl.fetchCars();
        System.out.print(cars);
        assert(!cars.isEmpty());
    }

}