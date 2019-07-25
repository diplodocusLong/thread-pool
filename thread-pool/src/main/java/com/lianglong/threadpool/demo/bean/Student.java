package com.lianglong.threadpool.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.Parser;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author lianglong
 * @date 19-7-25
 */

//springboot 1.5版本 localdate localDatetime需要自己做映射
//springboot 2.0版本 全自动

@Data
@Accessors(chain = true)
public class Student {

    Integer id ;

    String name;

    Integer age;

    LocalDateTime birthday;

}
