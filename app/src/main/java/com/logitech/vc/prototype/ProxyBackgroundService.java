package com.logitech.vc.prototype;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;

public class ProxyBackgroundService extends Service {
    public ProxyBackgroundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Util.log("background service create" );

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (;;) {
                        Thread.sleep(3000);
                        Util.log("background service run" );
                    }

                }catch (Exception e) {
                    Util.log("background service error" );
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Util.log("background service destroy" );
    }
}
