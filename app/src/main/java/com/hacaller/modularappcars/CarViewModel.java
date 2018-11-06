package com.hacaller.modularappcars;

import com.hacaller.business.Car;
import com.hacaller.business.CarCaseExecutorCanary;
import com.hacaller.business.CarCaseExecutorVanilla;
import com.hacaller.business.CarUseCase;
import com.hacaller.business.UseCaseObserver;

import java.util.List;

/**
 * Created by Herbert Caller on 05/11/2018.
 */
public class CarViewModel {

    CarListAdapter carListAdapter = new CarListAdapter();

    public void loadCars(){

        CarCaseExecutorCanary carCaseExecutor = ApplicationEntryPoint.getInstance().getCarCaseExecutor();
        carCaseExecutor.setCarUseCase(CarUseCase.GetAllCars);
        carCaseExecutor.setUseCaseObserver(new UseCaseObserver<List<Car>>() {
            @Override
            public void onSuccess(List<Car> carList) {
                // From Business Layer
                //  |
                //  |
                //  V
                // Presentation Layer
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
