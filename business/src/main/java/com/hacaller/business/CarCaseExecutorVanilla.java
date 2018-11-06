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
    int optional;

    public CarCaseExecutorVanilla setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
        return this;
    }

    public CarCaseExecutorVanilla setCarUseCase(CarUseCase carUseCase) {
        this.carUseCase = carUseCase;
        return this;
    }

    public void setCarUseCase(CarUseCase carUseCase , int optional) {
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
        executor.submit(futureTask);
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
                return carRepository.getTopRatedCars();
            }
        };
    }

    public Runnable setCarRating(final int id) {
        return new Runnable() {
            @Override
            public void run() {
                carRepository.setCarRating(id);
            }
        };
    }

}
