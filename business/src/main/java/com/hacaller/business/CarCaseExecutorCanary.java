package com.hacaller.business;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
 * Created by Herbert Caller on 06/11/2018.
 * For API24 and beyond
 */
public class CarCaseExecutorCanary extends UseCaseExecutorFactory {

    CompletableFuture completableFuture;

    @Override
    public void execute() {
        if (task == null) return;
        showWorkingThread();
        if (task instanceof Runnable) {
            completableFuture = CompletableFuture.runAsync((Runnable) task, executor);
            completableFuture.whenComplete(new BiConsumer() {
                @Override
                public void accept(Object o, Object o2) {
                    if (useCaseObserver == null) return;
                    useCaseObserver.onSuccess(o);
                    showWorkingThread();
                    showElapsedTime();
                }
            });
        }

        executor.shutdown();
    }
}
