package com.lianglong.threadpool.demo.controller;

import com.lianglong.threadpool.demo.bean.Student;
import com.lianglong.threadpool.demo.service.AsycnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
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


    @GetMapping("student")
    public Student getStudent() {

       return new Student()
                          .setAge(10)
                          .setId(1)
                          .setName("张三")
                          .setBirthday(LocalDateTime.now());
    }

    @PostMapping("update")
    public Student updateStudent(@RequestBody  Student student) {

      return student;



    }

    public static void main(String[] args) {


        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());

        int minute = calendar.get(Calendar.MINUTE);

        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR) - 1);

        calendar.add(Calendar.MINUTE,-minute%5);

        String[] arrs = new String[12];

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");


        for(int i=0;i<arrs.length;i++){

            calendar.add(Calendar.MINUTE,5);

            arrs[i] = simpleDateFormat.format(calendar.getTime());

            System.out.println(arrs[i]);

        }

    }

}
