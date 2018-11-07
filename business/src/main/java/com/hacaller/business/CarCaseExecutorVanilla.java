package com.hacaller.business;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Herbert Caller on 04/11/2018.
 * Without RxJava
 */
public class CarCaseExecutorVanilla extends UseCaseExecutorFactory {

    CarRepository carRepository;
    private FutureTask futureTask;
    CarUseCase carUseCase;
    Car optional;

    public CarCaseExecutorVanilla setCarRepository(CarRepository carRepository) {
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

    public void execute(){
        if (carUseCase == null) return;
        showWorkingThread();
        switch(carUseCase){
            case GetAllCars:
                futureTask = createFutureTask(getAllCars());
                executor.submit(futureTask);
                break;
            case GetTopRatedCars:
                futureTask = createFutureTask(getTopRatedCars());
                executor.submit(futureTask);
                break;
            case SetCarRating:
                executor.submit(setCarRating(optional));
                break;
        }
        try {
            if (useCaseObserver == null) return;
            if (futureTask == null) return;
            useCaseObserver.onSuccess(futureTask.get());
            showWorkingThread();
            showElapsedTime();
        } catch (InterruptedException | ExecutionException e) {
            if (useCaseObserver == null) return;
            useCaseObserver.onFailure(e);
        }
        executor.shutdown();
    }

    public Callable<List<Car>> getAllCars() {
        return new Callable<List<Car>>() {
            @Override
            public List<Car> call() {
                System.out.println("BusinessLogicThread-->BlackBox: "+Thread.currentThread());
                return carRepository.getCarList();
            }
        };
    }

    public Callable<List<Car>>  getTopRatedCars() {
        return new Callable<List<Car>>() {
            @Override
            public List<Car> call() throws Exception {
                System.out.println("BusinessLogicThread-->BlackBox: "+Thread.currentThread());
                return carRepository.getCarList();
            }
        };
    }

    public Runnable setCarRating(final Car car) {
        return new Runnable() {
            @Override
            public void run() {
                carRepository.updateCar(car);
            }
        };
    }

}
