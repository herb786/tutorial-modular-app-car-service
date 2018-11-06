package com.hacaller.business;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by Herbert Caller on 04/11/2018.
 * Without RxJava
 */
public class CarCaseExecutor {

    private long elapsed = System.currentTimeMillis();
    private UseCaseObserver useCaseObserver;
    //private Executor executor = Executors.newSingleThreadExecutor();
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private FutureTask futureTask;
    private Object task;

    public CarCaseExecutor setUseCaseObserver(UseCaseObserver useCaseObserver) {
        this.useCaseObserver = useCaseObserver;
        return this;
    }

    public CarCaseExecutor setUseCaseTask(Object task) {
        this.task = task;
        return this;
    }

    public void execute(){
        if (task == null) return;
        System.out.println("BusinessLogicThread-->Start: "+Thread.currentThread());
        if (task instanceof Callable){
            futureTask = createFutureTask((Callable) task);
            executor.submit(futureTask);
        } else if (task instanceof Runnable) {
            executor.submit((Runnable) task);
        }
        try {
            if (useCaseObserver == null) return;
            if (futureTask == null) return;
            useCaseObserver.onSuccess(futureTask.get());
            System.out.println("BusinessLogicThread-->End: "+Thread.currentThread());
            elapsed = System.currentTimeMillis() - elapsed;
            System.out.println("BusinessLogicThread-->Elapsed: "+elapsed+" ms");
        } catch (InterruptedException | ExecutionException e) {
            if (useCaseObserver == null) return;
            useCaseObserver.onFailure(e);
        }
        executor.shutdown();
    }

    private <T> FutureTask createFutureTask(Callable<T> callable){
        return new FutureTask<T>(callable);
    }

}
