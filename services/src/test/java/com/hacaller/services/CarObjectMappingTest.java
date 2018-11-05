package com.hacaller.services;

import com.hacaller.data.CarData;
import com.hacaller.services.models.CarResponse;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public class CarObjectMappingTest {

    CarResponse carResponseWithoutPhoto= ModelFactory.newCarResponse(false);
    CarResponse carResponseWithPhoto= ModelFactory.newCarResponse(true);
    CarData car= ModelFactory.newCar();

    @Test
    public void shouldBeCarResponseModel() {
        CarResponse newCar = CarObjectMapping.toCarResponseModel(car);
        assertEquals(newCar.getId(), car.getId());
    }

    @Test
    public void shouldHaveCarWithoutPhoto() {
        CarData newCar= CarObjectMapping.toCarModel(carResponseWithoutPhoto);
        assertEquals(newCar.getCarPhoto(), "");
    }

    @Test
    public void shouldHaveCarWithPhoto() {
        CarData newCar = CarObjectMapping.toCarModel(carResponseWithPhoto);
        assertEquals(newCar.getCarPhoto(), carResponseWithPhoto.getCarPhotos().get(0).getPhoto());
    }

}