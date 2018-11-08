package com.hacaller.abusiness;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.hacaller.business.UseCaseExecutorFactory;
import com.hacaller.business.UseCaseObserver;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by Herbert Caller on 06/11/2018.
 */
public abstract class UseCaseHermesFactory {

    Handler uiHandler = new Handler(Looper.getMainLooper());

    private long elapsed = System.currentTimeMillis();
    public ExecutorService executor = Executors.newSingleThreadExecutor();
    public UseCaseObserver useCaseObserver;

    public UseCaseHermesFactory setUseCaseObserver(final UseCaseObserver useCaseObserver) {
        this.useCaseObserver = useCaseObserver;
        uiHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                afterCompletedTask(msg);
            }
        };
        return this;
    }

    public abstract void afterCompletedTask(Message msg);

    public void execute(){
        executeBody();
    }

    public abstract void executeBody();


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
