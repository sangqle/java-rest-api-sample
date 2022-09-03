package com.sangle.common.logger;

import org.apache.logging.log4j.Logger;

import com.sangle.common.worker.MyThreadPool;

import org.apache.logging.log4j.LogManager;

public class TestLog {
    private static final Logger _Logger = LogManager.getLogger();

    public static void testException() {
        try {
            Integer.valueOf("hishfi");
        } catch (Exception ex) {
            _Logger.error(ex.getMessage(), ex);
            System.err.println(ex.getStackTrace().toString());
        }
    }

    public static void call() {
       
    }
    private static Object doSomeCalculation() {
        System.err.println("Hello from do some calculation");
        return new Object();
    }
    public static void main(String[] args) {
        for(int i = 0; i < 10_000_000; i++) {
            MyThreadPool.Instance.asynTestLog();
        }
        
    }
}
