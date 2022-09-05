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
            System.err.println(ex.getMessage());
            _Logger.info("this is info");
            _Logger.error(ex.getMessage(), ex);
            _Logger.debug("this is error");
        }
    }

    public static void call() {
       
    }
    private static Object doSomeCalculation() {
        System.err.println("Hello from do some calculation");
        return new Object();
    }
    public static void main(String[] args) {

    }
}

