package com.hacaller.business;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public class CarCaseFactoryTest {

    @Mock
    CarRepository carRepository;

    CarCaseFactory carCaseFactory;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        carCaseFactory = new CarCaseFactory(carRepository);
    }

    @Test
    public void getAllCars() {
        carCaseFactory.getAllCars();
        verify(carRepository, atLeastOnce()).getCarList();
    }

    @Test
    public void getTopRatedCars() {
        carCaseFactory.getTopRatedCars();
        verify(carRepository, atLeastOnce()).getTopRatedCars();
    }

    @Test
    public void setCarRating() {
        carCaseFactory.setCarRating(anyInt());
        verify(carRepository, atLeastOnce()).setCarRating(anyInt());
    }
}