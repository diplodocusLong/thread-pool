package com.lianglong.threadpool.demo.controller;

import com.lianglong.threadpool.demo.service.AsycnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author lianglong
 * @date 19-7-4
 */
@RestController
@Slf4j
public class Hello {
    @Autowired
    private AsycnService asyncService;

    @RequestMapping("abc")
    public Date submit() {

        log.info("start submit");

        //调用service层的任务
        Future<Date> dateFuture = asyncService.executeAsync();

        log.info("end submit");

        for (int i = 0; i < 100; i++) {
            System.out.println(i);

        }

        Date date = null;
        try {
            date = dateFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return date;
    }

    @GetMapping("thread")
    public String testThread() {

        asyncService.printAr();
        asyncService.printBr();
        return "success";
    }
}
