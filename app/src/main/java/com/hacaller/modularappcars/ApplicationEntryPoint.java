package com.hacaller.modularappcars;

import android.app.Application;

import com.hacaller.abusiness.CarCaseHermes;
import com.hacaller.business.CarRepository;
import com.hacaller.data.CarDao;
import com.hacaller.data.CarRepositoryImpl;
import com.hacaller.data.CarService;
import com.hacaller.database.CarDaoImpl;
import com.hacaller.services.ApiAdapter;
import com.hacaller.services.CarApiAdapter;
import com.hacaller.services.CarServiceImpl;

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
        application = this;
    }

    // Without DI Container

    // From Client Layer /*View Model*/
    //  |
    //  |
    //  V
    // Business Layer
    CarCaseHermes getCarCaseExecutor(){
        return new CarCaseHermes().setCarRepository(getCarRepository());
    }

    //  |
    //  |
    //  V
    // Persistence Layer
    CarRepository getCarRepository(){
        return new CarRepositoryImpl(getCarService(), getCarDao());
    }

    //  |
    //  |
    //  V
    // Service/DAO Layer
    //CarDao getAndroidCarDao() {return new CarDaoImpl(application); }
    CarDao getCarDao() {return new CarDaoImpl(application); }
    CarService getCarService(){
        return new CarServiceImpl(getApiAdapter());
    }

    ApiAdapter getApiAdapter(){
        return new CarApiAdapter();
    }

}
