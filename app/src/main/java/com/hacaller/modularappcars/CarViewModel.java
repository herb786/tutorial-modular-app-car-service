package com.hacaller.modularappcars;

import com.hacaller.business.Car;
import com.hacaller.business.CarCaseExecutor;
import com.hacaller.business.UseCaseObserver;

import java.util.List;

/**
 * Created by Herbert Caller on 05/11/2018.
 */
public class CarViewModel {

    CarListAdapter carListAdapter = new CarListAdapter();

    public void loadCars(){

        CarCaseExecutor carCaseExecutor = new CarCaseExecutor();
        carCaseExecutor.setUseCaseTask(ApplicationEntryPoint.getInstance().getCarCaseFactory().getAllCars());
        carCaseExecutor.setUseCaseObserver(new UseCaseObserver<List<Car>>() {
            @Override
            public void onSuccess(List<Car> carList) {
                if (carListAdapter != null) carListAdapter.setCarList(carList);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

        carCaseExecutor.execute();
    }


    public CarListAdapter getCarListAdapter() {
        return carListAdapter;
    }
}
