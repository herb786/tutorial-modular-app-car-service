package com.hacaller.modularappcars;

import android.app.Application;

import com.hacaller.business.CarUseCases;
import com.hacaller.business.CarRepository;
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
    CarUseCases getCarCaseFactory(){
        return new CarUseCases(getCarRepository());
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
