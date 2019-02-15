package com.logitech.vc.prototype;

import android.app.job.JobParameters;
import android.app.job.JobService;

public class ProxyJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {

        Util.log("JobService start" );
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (;;) {
                        Thread.sleep(3000);
                        Util.log("JobService run" );
                    }
                }catch (Exception e) {
                    Util.log("JobService error" );
                }
            }
        }).start();

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Util.log("JobService stop" );
        return false;
    }
}
