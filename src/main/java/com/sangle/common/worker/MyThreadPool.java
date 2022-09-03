package com.sangle.common.worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sangle.common.entity.User;
import com.sangle.common.logger.TestLog;
import com.sangle.common.mysql.UserMysql;

public class MyThreadPool {
    public static MyThreadPool Instance = new MyThreadPool();
    private static final Logger _Logger = LogManager.getLogger();

    private MyThreadPool() {
    }

    public ExecutorService _threadPool = new ThreadPoolExecutor(
            100,
            500,
            120,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>());

    public void asynAddUser(final User newUser) {
        try {
            _threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    int addOrUpdate = 0;
                    addOrUpdate = UserMysql.Instance.add(newUser);
                    if (addOrUpdate <= 0) {
                        _Logger.error("Can not add pair appId: " + newUser.getAppId());
                    }
                    _Logger.info("Add user: " + newUser);
                }
            });
        } catch (Exception ex) {
            _Logger.error(ex.getMessage(), ex);
        }
    }

    public void asynTestLog() {
        try {
            _threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    TestLog.testException();
                }
            });
        } catch (Exception ex) {
        }
    }
}
