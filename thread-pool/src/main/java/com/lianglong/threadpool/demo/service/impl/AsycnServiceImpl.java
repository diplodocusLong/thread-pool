package com.lianglong.threadpool.demo.service.impl;

import com.lianglong.threadpool.demo.service.AsycnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.validation.constraints.AssertFalse;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lianglong
 */
@Slf4j
@Service
public class AsycnServiceImpl implements AsycnService {


    private Lock lock = new ReentrantLock();

    private Condition condA = lock.newCondition();

    private Condition condB = lock.newCondition();

    private Integer number = 0;

    private volatile boolean flag = false;

    @Override
    @Async(value = "asyncServiceExecutor")
    public Future<Date> executeAsync() {
        log.info("start executeAsync");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("end executeAsync");

        return new AsyncResult<Date>(new Date());
    }

    @Override
    @Async(value = "asyncServiceExecutor")
    public void printA() {

        for (int i = 0; i < 10; i++) {
            try {
                lock.lock();

                while (number != 0) {
                    condA.await();
                }
                System.out.println('A');

                number = 1;

                condB.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

                lock.unlock();
            }
        }


    }

    @Override
    @Async(value = "asyncServiceExecutor")
    public void printB() {

        for (int i = 0; i < 10; i++) {
            try {
                lock.lock();

                while (number != 1) {
                    condB.await();
                }
                System.out.println('B');
                number = 0;
                condA.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

    @Override
    @Async
    public void printAr() {

        for (int i = 0; i < 10; i++) {

            while (!flag) {
                System.out.println("A");
                flag = true;
                break;
            }



        }

    }

    @Override
    public void printBr() {
        for (int i = 0; i < 10; i++) {
            while (flag) {
                System.out.println("B");
                flag = false;
            }
        }

    }

}