package com.lianglong.amqp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author lianglong
 * @date 2019/8/7
 */
@Configuration
public class RabbitmqConfig implements ConfirmCallback, ReturnCallback{
     Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RabbitTemplate rabbitTemplate;


    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);            //指定 ConfirmCallback
        rabbitTemplate.setReturnCallback(this);             //指定 ReturnCallback

    }



        /**
         * 定义一个队列
         * queue 可以有4个参数
         *    1.队列名
         *    2.durable     持久化消息队列，rabbitmq重启的时候不需要创建新的队列 默认为true
         *    3.auto-delete 表示消息队列没有在使用时将被自动删除，默认为false
         *    4.exclusive   表示该消息队列是否只在当前connection生效，默认是false
         * @return
         */
    @Bean
    public Queue getQueue(){
        return new Queue("phoneQueue");
    }


    /** ==============定制处理策略========== */

    /**
     * 定制化amqp模板
     * ConfirmCallback 接口用于实现消息发送到RabbitMq交换器后接收ack回调  即消息发送到exchange
     * ReturnCallback 接口用于实现消息发送到RabbitMq交换器，但无相应队列与交换机绑定时的回调 即消息发送不到任何一个队列中
     * @return
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        String correlationIdString = message.getMessageProperties().getCorrelationIdString();

        log.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationIdString, replyCode, replyText, exchange, routingKey);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            log.info("消息发送成功，id:{}",correlationData.getId());
        }else{
            log.info("消息发送失败，原因：{}",cause);
        }
    }

    @Bean
    public MessageConverter messageConverter(){

        return new Jackson2JsonMessageConverter();
    }
}
