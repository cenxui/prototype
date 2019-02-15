package com.logitech.vc.prototype;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProxyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        // This method is called when the BroadcastReceiver is receiving
        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            // run background service
            Util.log("receive boot completed event");
            startService(context);
        }
    }

    private void startService(Context context) {
        try {
            Intent i = new Intent(context, ProxyService.class);
            context.startForegroundService(i);
        }catch (Exception e) {
            Util.log("receive start service  error :" +e.getMessage());
        }
    }

    private void startJobService(Context context) {
        try {
            JobInfo.Builder builder = new JobInfo.Builder(1337, new ComponentName(context, ProxyJobService.class))
                    .setOverrideDeadline(0);
            JobScheduler tm = (JobScheduler)context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            tm.schedule(builder.build());
        }catch (Exception e) {
            Util.log("receive start job  error :" +e.getMessage());
        }
    }
}
