package com.lianglong.threadpool.demo.service;


import org.springframework.scheduling.annotation.Async;

import java.util.Date;
import java.util.concurrent.Future;

/**
 * @author lianglong
 */

public interface AsycnService {

    /**
     * 执行异步任务
     * @return
     */
    Future<Date> executeAsync();



    void printA();

    void printB();

    void printAr();

    void printBr();
}
