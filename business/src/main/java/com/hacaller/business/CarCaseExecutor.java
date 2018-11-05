package com.hacaller.business;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public class CarCaseExecutor<T> {

    private CaseObserver<T> caseObserver;
    //private Executor executor = Executors.newSingleThreadExecutor();
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private FutureTask<T> futureTask;
    private Object task;

    public CarCaseExecutor setCaseObserver(CaseObserver<T> caseObserver) {
        this.caseObserver = caseObserver;
        return this;
    }

    public CarCaseExecutor setAsyncTask(Object task) {
        this.task = task;
        return this;
    }

    public void execute(){
        if (task == null) return;
        System.out.println("BusinessLogicThread-->Start-->"+Thread.currentThread());
        if (task instanceof Callable){
            futureTask = createFutureTask((Callable<T>) task);
            executor.submit(futureTask);
        } else if (task instanceof Runnable) {
            executor.submit((Runnable) task);
        }
        try {
            if (caseObserver == null) return;
            if (futureTask == null) return;
            caseObserver.onSuccess(futureTask.get());
            System.out.println("BusinessLogicThread-->End-->"+Thread.currentThread());
        } catch (InterruptedException | ExecutionException e) {
            if (caseObserver == null) return;
            caseObserver.onFailure(e);
        }
        executor.shutdown();
    }

    private <T> FutureTask createFutureTask(Callable<T> callable){
        return new FutureTask<T>(callable);
    }

}
