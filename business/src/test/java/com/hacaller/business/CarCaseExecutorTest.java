package com.hacaller.business;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Created by Herbert Caller on 04/11/2018.
 */
public class CarCaseExecutorTest {

    CaseObserver caseObserver = new CaseObserver<List<Car>>() {
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
    public void testThreads(){
        CarRepository carRepository = mock(CarRepository.class);
        CarCaseFactory caseFactory = new CarCaseFactory(carRepository);
        CarCaseExecutor carCaseExecutor = new CarCaseExecutor();
        carCaseExecutor.setCaseObserver(caseObserver);
        carCaseExecutor.setAsyncTask(caseFactory.getAllCars());
        carCaseExecutor.execute();
        //verify(carRepository, atLeastOnce()).getCarList();
    }


}