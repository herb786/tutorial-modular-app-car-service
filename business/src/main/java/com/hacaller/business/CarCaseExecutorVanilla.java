package com.hacaller.business;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Herbert Caller on 04/11/2018.
 * Without RxJava
 */
public class CarCaseExecutorVanilla extends UseCaseExecutorFactory {

    private FutureTask futureTask;

    public void execute(){
        if (task == null) return;
        showWorkingThread();
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
            showWorkingThread();
            showElapsedTime();
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
