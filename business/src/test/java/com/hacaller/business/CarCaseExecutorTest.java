package com.hacaller.business;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Created by Herbert Caller on 04/11/2018.
 */
public class CarCaseExecutorTest {

    UseCaseObserver useCaseObserver = new UseCaseObserver<List<Car>>() {
        @Override
        public void onSuccess(List<Car> object) {
            System.out.println("CaseObserverThread-->"+Thread.currentThread());
        }

        @Override
        public void onFailure(Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
    };

    @Test
    public void testVanillaThreads(){
        CarRepository carRepository = mock(CarRepository.class);
        CarCaseExecutorVanilla carCaseExecutor = new CarCaseExecutorVanilla();
        carCaseExecutor.setUseCaseObserver(useCaseObserver);
        carCaseExecutor.setCarRepository(carRepository);
        carCaseExecutor.setCarUseCase(CarUseCase.GetAllCars);
        carCaseExecutor.execute();
        //verify(carRepository, atLeastOnce()).getCarList();
    }

    @Test
    public void testCanaryThreads(){
        CarRepository carRepository = mock(CarRepository.class);
        CarCaseExecutorCanary carCaseExecutor = new CarCaseExecutorCanary();
        carCaseExecutor.setUseCaseObserver(useCaseObserver);
        carCaseExecutor.setCarRepository(carRepository);
        carCaseExecutor.setCarUseCase(CarUseCase.GetAllCars);
        carCaseExecutor.execute();
        verify(carRepository, atLeastOnce()).getCarList();
    }


}