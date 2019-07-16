package com.lianglong.amqp.service;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class RabbitmqSend {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    JavaMailSenderImpl sender;

       @Scheduled(cron = "* 0/30 * * * *")
       public void sentMessage(){

           rabbitTemplate.convertAndSend("exchange.direct","lianglong", new Date());

       }




       @RabbitListener(queues = "lianglong")
       public void consumerMessage(Date time){

           MimeMessage mimeMessage = sender.createMimeMessage();

           MimeMessageHelper mimeMessageHelper = null;
           try {
               mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

               mimeMessageHelper.setSubject("系统邮件勿直接回复");

               mimeMessageHelper.setFrom("nevermore_sf@aliyun.com");

               mimeMessageHelper.setTo("18510126271@163.com");

               mimeMessageHelper.addAttachment("Bye Bye Beautiful.mp3",new File("/home/nevermore/音乐/myMusic/nightwish/Bye Bye Beautiful.mp3"));

               mimeMessageHelper.setText(""+time+"/n"+ LocalDateTime.now());

               sender.send(mimeMessage);
           } catch (MessagingException e) {
               e.printStackTrace();
           }



       }

}
