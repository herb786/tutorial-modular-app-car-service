package com.hacaller.abusiness;

import android.os.Looper;
import android.os.Message;

import com.hacaller.business.Car;
import com.hacaller.business.CarRepository;
import com.hacaller.business.CarUseCase;

import java.util.List;

/**
 * Created by Herbert Caller on 04/11/2018.
 * Without RxJava
 */
public class CarCaseHermes extends UseCaseHermesFactory {

    CarRepository carRepository;
    CarUseCase carUseCase;
    Car optional;

    public CarCaseHermes setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
        return this;
    }

    public void setCarUseCase(CarUseCase carUseCase) {
        this.carUseCase = carUseCase;
    }

    public void setCarUseCase(CarUseCase carUseCase , Car optional) {
        this.carUseCase = carUseCase;
        this.optional = optional;
    }

    @Override
    public void afterCompletedTask(Message msg) {
        useCaseObserver.onSuccess(msg.obj);
        showWorkingThread();
        showElapsedTime();
    }

    @Override
    public void executeBody() {
        showWorkingThread();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                if (carUseCase == null) return;
                switch(carUseCase){
                    case GetAllCars:
                        getAllCars();
                        break;
                    case GetTopRatedCars:
                        getTopRatedCars();
                        break;
                    case SetCarRating:
                        setCarRating(optional);
                        break;
                }
            }
        });
        executor.shutdown();
    }

    public void getAllCars() {
        System.out.println("BusinessLogicThread-->BlackBox: "+Thread.currentThread());
        //setDelay();
        List<Car> cars =  carRepository.getCarList();
        // Hey UiThread I need to clock out, the task is done
        Message m = uiHandler.obtainMessage(1, cars);
        m.sendToTarget();
    }

    public void getTopRatedCars() {
        System.out.println("BusinessLogicThread-->BlackBox: "+Thread.currentThread());
        //setDelay();
        List<Car> cars = carRepository.getCarList();
        Message m = uiHandler.obtainMessage(1, cars);
        m.sendToTarget();
    }

    public void setCarRating(Car car) {
        carRepository.updateCar(car);
        Message m = uiHandler.obtainMessage(1);
        m.sendToTarget();
    }

    void setDelay(){
        long st  = System.currentTimeMillis();
        long time = System.currentTimeMillis() - st;
        int i = 1;
        while (time / 5000 < 1){
            if (time % 1000 == 0 && time/1000 == i){
                i++;
                System.out.println("BusinessLogicThread--> working time: " + time / 1000);
            }
            time = System.currentTimeMillis() - st;
        }
    }

}
