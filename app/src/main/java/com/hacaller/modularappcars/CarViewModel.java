package com.hacaller.modularappcars;

import com.hacaller.business.Car;
import com.hacaller.business.CarCaseExecutor;
import com.hacaller.business.CarCaseFactory;
import com.hacaller.business.CarRepository;
import com.hacaller.business.CaseObserver;
import com.hacaller.data.CarRepositoryImpl;
import com.hacaller.services.CarServiceImpl;
import com.hacaller.services.ServiceFactory;

import java.util.List;

/**
 * Created by Herbert Caller on 05/11/2018.
 */
public class CarViewModel {

    CarListAdapter carListAdapter = new CarListAdapter();

    public void loadCars(){
        CarCaseExecutor<List<Car>> carCaseExecutor = new CarCaseExecutor<List<Car>>();
        CarRepository carRepository = new CarRepositoryImpl(new CarServiceImpl(new ServiceFactory()));
        CarCaseFactory caseFactory = new CarCaseFactory(carRepository);
        carCaseExecutor.setCaseObserver(new CaseObserver<List<Car>>() {
            @Override
            public void onSuccess(List<Car> carList) {
                if (carListAdapter != null) carListAdapter.setCarList(carList);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
        carCaseExecutor.setAsyncTask(caseFactory.getAllCars());
        carCaseExecutor.execute();
    }


    public CarListAdapter getCarListAdapter() {
        return carListAdapter;
    }
}
