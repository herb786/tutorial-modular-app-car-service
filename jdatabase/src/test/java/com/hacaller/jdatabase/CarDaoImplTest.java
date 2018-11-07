package com.hacaller.jdatabase;

import com.hacaller.data.CarData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Herbert Caller on 07/11/2018.
 */
public class CarDaoImplTest {

    String databaseLocation = "CarApp.db";
    CarData carData1;
    CarData carData2;
    CarDaoImpl carDao;

    @Before
    public void setup() {
        carDao = new CarDaoImpl(databaseLocation);
        carDao.setDatabaseLocation(databaseLocation);
        CarDatabaseUtils.createCarDatabase(databaseLocation);
        carData1 = new CarData(1, "logo","url",1492,
                "Watson","photo",5);
        carData2 = new CarData(6, "logo","url",1492,
                "Watson","photo",5);
        carDao.saveCar(carData1);
        carDao.saveCar(carData2);
    }

    @Test
    public void shouldGetCars() {
        List<CarData> cars = carDao.getCars();
        assertEquals(cars.size(),2);
    }

    @Test
    public void shouldUpdateCar() {
        carData1.setRating(2);
        carDao.updateCar(carData1);
        CarData carData = carDao.fetchCar(carData1.getId());
        assertEquals(carData.getRating(), 2);
    }

    @After
    public void tearDown(){
        carDao.deleteCar(carData1);
        carDao.deleteCar(carData2);
    }
}