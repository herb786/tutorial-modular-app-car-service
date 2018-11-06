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
public class CarUseCasesTest {

    @Mock
    CarRepository carRepository;

    CarUseCases carUseCases;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        carUseCases = new CarUseCases(carRepository);
    }

    @Test
    public void getAllCars() {
        carUseCases.getAllCars();
        verify(carRepository, atLeastOnce()).getCarList();
    }

    @Test
    public void getTopRatedCars() {
        carUseCases.getTopRatedCars();
        verify(carRepository, atLeastOnce()).getTopRatedCars();
    }

    @Test
    public void setCarRating() {
        carUseCases.setCarRating(anyInt());
        verify(carRepository, atLeastOnce()).setCarRating(anyInt());
    }
}