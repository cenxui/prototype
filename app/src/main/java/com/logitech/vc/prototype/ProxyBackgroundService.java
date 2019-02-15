package com.logitech.vc.prototype;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ProxyBackgroundService extends Service {
    public ProxyBackgroundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

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
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Util.log("background service destroy" );
        super.onDestroy();
//        Intent i = new Intent(this, ProxyBackgroundService.class);
//        this.startService(i);
    }
}
