package com.hacaller.business;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Herbert Caller on 06/11/2018.
 */
public abstract class UseCaseExecutorFactory {

    private long elapsed = System.currentTimeMillis();
    ExecutorService executor = Executors.newSingleThreadExecutor();
    UseCaseObserver useCaseObserver;
    Object task;

    public UseCaseExecutorFactory setUseCaseObserver(UseCaseObserver useCaseObserver) {
        this.useCaseObserver = useCaseObserver;
        return this;
    }

    public UseCaseExecutorFactory setUseCaseTask(Object task) {
        this.task = task;
        return this;
    }

    public abstract void execute();


    void showElapsedTime(){
        elapsed = System.currentTimeMillis() - elapsed;
        System.out.println("BusinessLogicThread-->Elapsed: "+elapsed+" ms");
    }

    void showWorkingThread(){
        System.out.println("BusinessLogicThread-->End: "+Thread.currentThread());
    }


}
