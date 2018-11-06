package com.hacaller.business;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Herbert Caller on 06/11/2018.
 * For API24 and beyond
 */
public class CarCaseExecutorCanary extends UseCaseExecutorFactory {

    CompletableFuture completableFuture;
    CarRepository carRepository;
    CarUseCase carUseCase;
    int optional;

    public CarCaseExecutorCanary setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
        return this;
    }

    public void setCarUseCase(CarUseCase carUseCase) {
        this.carUseCase = carUseCase;
    }

    public void setCarUseCase(CarUseCase carUseCase , int optional) {
        this.carUseCase = carUseCase;
        this.optional = optional;
    }

    @Override
    public void execute() {
        //if (task == null) return;
        showWorkingThread();
        switch(carUseCase){
            case GetAllCars:
                completableFuture = createCompletableAllCars();
                break;
            case GetTopRatedCars:
                completableFuture = createCompletableTopRatedCars();
                break;
            case SetCarRating:
                completableFuture = createCompletableSetCarRating();
                break;
        }
        try {
            useCaseObserver.onSuccess(completableFuture.get());
            showWorkingThread();
            showElapsedTime();
        } catch (InterruptedException | ExecutionException e) {
            if (useCaseObserver == null) return;
            useCaseObserver.onFailure(e);
        }
        executor.shutdown();
    }

    CompletableFuture<List<Car>> createCompletableAllCars(){
        return CompletableFuture.supplyAsync(new Supplier<List<Car>>() {
            @Override
            public List<Car> get() {
                return carRepository.getCarList();
            }
        });
    }

    CompletableFuture<List<Car>> createCompletableTopRatedCars(){
        return CompletableFuture.supplyAsync(new Supplier<List<Car>>() {
            @Override
            public List<Car> get() {
                return carRepository.getTopRatedCars();
            }
        }).thenApply(new Function<List<Car>, List<Car>>() {
            @Override
            public List<Car> apply(List<Car> carList) {
                return carList;
            }
        });
    }

    CompletableFuture createCompletableSetCarRating(){
        return CompletableFuture.supplyAsync(new Supplier<Void>() {
            @Override
            public Void get() {
                carRepository.setCarRating(optional);
                return null;
            }
        });
    }




}
