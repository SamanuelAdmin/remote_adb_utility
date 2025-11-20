package com.example.remoteadbutility;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.remoteadbutility.content.Manager;


public class LoaderService extends IntentService {
    // Service-loader for
    private static String TAG = "LoaderService";
    private Manager manager;

    public LoaderService() {
        super("LoaderService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Log.i("RemoteADBUtilityINFO", "Starting remote adb utility (manager)...");
        this.manager = new Manager();

        Thread managerThread = new Thread(this.manager);
        managerThread.start();

        while (true) {
            if (this.manager.isStarted()) break;

            try { Thread.sleep(1000);
            } catch (InterruptedException e) {  }
        }

        Log.i("RemoteADBUtilityINFO", "Remote ADB utility started.");

        // Make service working in daemon on the background
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // start itself to ensure our broadcast receiver is active
        Log.d(TAG, "Start clipboard service.");
        // starting on-create
        startService(new Intent(getApplicationContext(), LoaderService.class));
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
    }
}
