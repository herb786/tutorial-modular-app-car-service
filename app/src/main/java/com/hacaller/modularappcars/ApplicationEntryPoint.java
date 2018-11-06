package com.hacaller.modularappcars;

import android.app.Application;

import com.hacaller.business.CarCaseExecutorCanary;
import com.hacaller.business.CarCaseExecutorVanilla;
import com.hacaller.business.CarUseCase;
import com.hacaller.business.CarRepository;
import com.hacaller.business.UseCaseExecutorFactory;
import com.hacaller.data.CarRepositoryImpl;
import com.hacaller.data.CarService;
import com.hacaller.services.CarServiceImpl;
import com.hacaller.services.ApiAdapter;
import com.hacaller.services.CarApiAdapter;

/**
 * Created by Herbert Caller on 06/11/2018.
 */
public class ApplicationEntryPoint extends Application {

    static ApplicationEntryPoint application;

    public static ApplicationEntryPoint getInstance(){
        if (application == null){
            application = new ApplicationEntryPoint();
        }
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    // Without DI Container

    // From Client Layer /*View Model*/
    //  |
    //  |
    //  V
    // Business Layer
    CarCaseExecutorVanilla getCarCaseExecutor(){
        return new CarCaseExecutorVanilla().setCarRepository(getCarRepository());
    }

    //  |
    //  |
    //  V
    // Persistence Layer
    CarRepository getCarRepository(){
        return new CarRepositoryImpl(getCarService());
    }

    //  |
    //  |
    //  V
    // Service Layer
    CarService getCarService(){
        return new CarServiceImpl(getApiAdapter());
    }

    ApiAdapter getApiAdapter(){
        return new CarApiAdapter();
    }

}
