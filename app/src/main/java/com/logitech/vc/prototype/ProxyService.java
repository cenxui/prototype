package com.logitech.vc.prototype;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

public class ProxyService extends Service {

    //todo service study
    public ProxyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Util.log("service create");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startMyOwnForeground();
        else
            startForeground(1, new Notification());

        String CHANNEL_ID = "prototype_Proxy";
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                "Proxy Service running",
                NotificationManager.IMPORTANCE_DEFAULT);

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "MyApp::MyWakelockTag");
        wakeLock.acquire();

        Util.log("service Wakelock" );

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (;;) {
                        Thread.sleep(3000);
                        Util.log("service run" );
                    }

                }catch (Exception e) {
                    Util.log("service error" );
                }
            }
        }).start();
    }

    private void startMyOwnForeground(){
        // NOTIFICATION_CHANNEL_ID must be this app package name
        String NOTIFICATION_CHANNEL_ID = "com.logitech.vc.prototype";
        String channelName = "My Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName,
                NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setContentTitle("App is running in background")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(1000, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Util.log("service destroy");

        // broadcast send event
    }

    @Override
    public IBinder onBind(Intent intent) {
        Util.log("service bind");
        return null;
    }
}
