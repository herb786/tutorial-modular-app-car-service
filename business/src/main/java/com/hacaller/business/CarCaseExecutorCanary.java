package com.hacaller.business;

import java.util.ArrayList;
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
    Car optional;

    public CarCaseExecutorCanary setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
        return this;
    }

    public void setCarUseCase(CarUseCase carUseCase) {
        this.carUseCase = carUseCase;
    }

    public void setCarUseCase(CarUseCase carUseCase, Car optional) {
        this.carUseCase = carUseCase;
        this.optional = optional;
    }

    @Override
    public void execute() {
        //if (task == null) return;
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
            if (useCaseObserver != null ) useCaseObserver.onSuccess(completableFuture.get());
            if (useCaseObserver == null ) completableFuture.get();
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
                showWorkingThread();
                return carRepository.getCarList();
            }
        }, executor);
    }

    CompletableFuture<List<Car>> createCompletableTopRatedCars(){
        return CompletableFuture.supplyAsync(new Supplier<List<Car>>() {
            @Override
            public List<Car> get() {
                showWorkingThread();
                return carRepository.getCarList();
            }
        }, executor).thenApply(new Function<List<Car>, List<Car>>() {
            @Override
            public List<Car> apply(List<Car> carList) {
                showWorkingThread();
                List<Car> cars = new ArrayList<>();
                for (Car o: carList){
                    if (o.rating > 3){
                        cars.add(o);
                    }
                }
                return cars;
            }
        });
    }

    CompletableFuture createCompletableSetCarRating(){
        return CompletableFuture.supplyAsync(new Supplier<Void>() {
            @Override
            public Void get() {
                showWorkingThread();
                carRepository.updateCar(optional);
                return null;
            }
        }, executor);
    }




}
