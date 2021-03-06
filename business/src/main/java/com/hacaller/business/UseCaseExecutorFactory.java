package com.hacaller.business;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by Herbert Caller on 06/11/2018.
 */
public abstract class UseCaseExecutorFactory {

    private long elapsed = System.currentTimeMillis();
    public ExecutorService executor = Executors.newSingleThreadExecutor();
    public UseCaseObserver useCaseObserver;

    public UseCaseExecutorFactory setUseCaseObserver(UseCaseObserver useCaseObserver) {
        this.useCaseObserver = useCaseObserver;
        return this;
    }

    public abstract void execute();


    public void showElapsedTime(){
        elapsed = System.currentTimeMillis() - elapsed;
        System.out.println("BusinessLogicThread-->Elapsed: "+elapsed+" ms");
    }

    public void showWorkingThread(){
        System.out.println("BusinessLogicThread-->Thread: "+Thread.currentThread());
    }

    public <T> FutureTask createFutureTask(Callable<T> callable){
        return new FutureTask<T>(callable);
    }


}
