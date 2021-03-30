package com.datum.android.todoapp2.util;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutor {

    /**
     * Object used for the purpose of synchronize lock
     */
    private static final Object LOCK = new Object();

    /**
     * Instance of this class for Singleton
     */
    private static AppExecutor sInstance;

    /**
     * Executor for local storage related operations
     */
    private final Executor diskIO;

    /**
     * Executor for network related operations
     */
    private final Executor networkIO;

    /**
     * Executor for main thread or UI operations
     */
    private final Executor mainThread;

    /**
     * Constructor use to initialize Executor instances
     *
     * @param diskIO     instance of Executor used for local storage operations
     * @param networkIO  instance of Executor for network operations
     * @param mainThread instance MainThreadExecutor to be used for main thread operations
     */
    public AppExecutor(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    /**
     * Method used to get an instance of AppExecutor class
     *
     * @return an instance of AppExecutor class
     */
    public static AppExecutor getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                // Create new object of AppExecutor
                if (sInstance == null) {
                    sInstance = new AppExecutor(
                            Executors.newSingleThreadExecutor(),
                            Executors.newFixedThreadPool(3),
                            new MainThreadExecutor()
                    );
                }
            }
        }
        return sInstance;
    }


    /**
     * Get instance of Executor class to use for local storage related operations
     *
     * @return instance of disk io executor
     */
    public Executor getDiskIO() {
        return diskIO;
    }

    /**
     * Get instance of Executor class to use for network related operations
     *
     * @return instance of network executor
     */
    public Executor getNetworkIO() {
        return networkIO;
    }

    /**
     * Get instance of Executor class to use for user interface and other main thread operations
     *
     * @return instance of main thread executor
     */
    public Executor getMainThread() {
        return mainThread;
    }

    /**
     * Custom Executor that handle operations on MainThread
     */
    private static class MainThreadExecutor implements Executor {

        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }

}
