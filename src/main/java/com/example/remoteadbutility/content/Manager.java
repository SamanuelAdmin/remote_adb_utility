package com.example.remoteadbutility.content;

import android.util.Log;

public class Manager implements Runnable {
    // Main class for inner business logic
    private static String TAG = "RADBU_Manager";
    private boolean started = false;

    public Manager() {

    }

    public boolean isStarted() { return this.started; }


    @Override
    public void run() {
        // will be started in new thread

        this.started = true;
    }
}
