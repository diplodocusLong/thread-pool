package com.lianglong.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * rabbitmq 自动配置
 * 1.RabbitAutoConfiguration 自动配置类
 * 配置了 rabbitmq链接工厂
 * rabbitTemplate 发送和接收消息
 * adminamqp 管理组件
 * 2.RabbitProperties 封装了配置属性  prefix=spring.rabbitmq
 */


@SpringBootApplication
@EnableRabbit
@EnableAsync
@EnableScheduling
public class SpringBootAmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAmqpApplication.class, args);
    }

}
