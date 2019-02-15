package com.logitech.vc.prototype;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class ProxyService extends Service {

    //todo service study
    public ProxyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Util.log("service create");

        String CHANNEL_ID = "my_channel_01";
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT);

        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

        Notification notification = new Notification.Builder(this, "Proxy Service")
                .setContentTitle("Proxy Service title")
                .setContentText("Proxy Service text")
                .build();
        try {
            this.startForeground(1, notification);
        }catch (Exception e) {
            Util.log("service startForeground error:" +e.getMessage());
        }

        try {
            for (;;) {
                Thread.sleep(3000);
                Util.log("service run" );
            }
        }catch (Exception e) {
            Util.log("service error" );
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Util.log("service destroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Util.log("service bind");
        return null;
    }
}
