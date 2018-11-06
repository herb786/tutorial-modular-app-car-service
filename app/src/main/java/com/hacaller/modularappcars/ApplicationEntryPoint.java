package com.hacaller.modularappcars;

import android.app.Application;

import com.hacaller.business.CarCaseFactory;
import com.hacaller.business.CarRepository;
import com.hacaller.data.CarRepositoryImpl;
import com.hacaller.data.CarService;
import com.hacaller.services.CarServiceImpl;
import com.hacaller.services.ServiceEndpoint;
import com.hacaller.services.ServiceFactory;

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
    CarCaseFactory getCarCaseFactory(){
        return new CarCaseFactory(getCarRepository());
    }

    CarRepository getCarRepository(){
        return new CarRepositoryImpl(getCarService());
    }

    CarService getCarService(){
        return new CarServiceImpl(getServiceEndpoint());
    }

    ServiceEndpoint getServiceEndpoint(){
        return ServiceFactory.buildRetrofitService();
    }

}
