package com.sangle.common.worker;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MySchedulerThreaPool {
    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    public static final MySchedulerThreaPool Instance = new MySchedulerThreaPool();

    private MySchedulerThreaPool() {
    }

    public void testScheduler() {
        try {
            ZonedDateTime now = ZonedDateTime.now();
            ZonedDateTime nextRun = now.withHour(10).withMinute(0).withSecond(0);
            if (now.compareTo(nextRun) > 0) {
                nextRun = nextRun.plusDays(1);
            }

            Duration duration = Duration.between(now, nextRun);
            long initialDelay = duration.getSeconds();
            executor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.err.println(ZonedDateTime.now());
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
            }, initialDelay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public static void main(String[] args) {
        Instance.testScheduler();
    }
}
