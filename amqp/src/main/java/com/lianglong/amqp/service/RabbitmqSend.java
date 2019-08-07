package com.lianglong.amqp.service;


import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class RabbitmqSend {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    JavaMailSenderImpl sender;

    private  Integer id=0;

    @Scheduled(cron = "0 */1 * * * ?")
    public void sentMessage() {
        rabbitTemplate.convertAndSend("exchange.direct", "lianglong", new Date(), new CorrelationData(id++ +""));
    }

    @RabbitListener(queues = "lianglong")
    public void consumerMessage(Date time, Channel channel, Message message) {
           /*MimeMessage mimeMessage = sender.createMimeMessage();
           MimeMessageHelper mimeMessageHelper = null;
           try {
               mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

               mimeMessageHelper.setSubject("系统邮件勿直接回复");

               mimeMessageHelper.setFrom("nevermore_sf@aliyun.com");

               mimeMessageHelper.setTo("18510126271@163.com");

               mimeMessageHelper.addAttachment("Bye Bye Beautiful.mp3",new File("/home/nevermore/音乐/myMusic/nightwish/Bye Bye Beautiful.mp3"));

               mimeMessageHelper.setText(""+time+"/n"+ LocalDateTime.now());

           } catch (MessagingException e) {
               e.printStackTrace();
           }
        return "收到";*/
        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.info("time is:{}", time);
        try {

            logger.info("更新数据库");

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (IOException e) {
            //如果是交还过的信息
            try {
                if (message.getMessageProperties().getRedelivered()) {

                    channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                } else {

                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);

                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}
