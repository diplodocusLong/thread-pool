package com.lianglong.amqp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Autowired
    AmqpAdmin amqpAdmin;


    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void sendMail(){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setSubject("通知今天开会");

        simpleMailMessage.setText("全体注意全体注意");

        simpleMailMessage.setTo("164449582@qq.com");

        simpleMailMessage.setFrom("nevermore_sf@aliyun.com");

        mailSender.send(simpleMailMessage);
    }




    @Test
    public void sendMessage() {

        Map<String,Object> map = new HashMap<>();
        map.put("code",3306);
        map.put("data", Arrays.asList("hello",1314,"goodman"));

          rabbitTemplate.convertAndSend("exchange.direct","lianglong",map);
    }

    @Test
    public void reseive(){

        Object map = rabbitTemplate.receiveAndConvert("lianglong");


        System.out.println(map.getClass());

        System.out.println(map);
    }


    //amqp admin 建路由  bunding等

    @Test
    public  void setRabbitmq(){

        Exchange exchange = new FanoutExchange("testExchange");

        amqpAdmin.declareExchange(exchange);


        Binding testExchange = new Binding("lianglong", Binding.DestinationType.QUEUE, "testExchange", "", null);

        amqpAdmin.declareBinding(testExchange);

    }


    @Test
    public void consumerMessage(){

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage,true);

            helper.setSubject("系统邮件勿直接回复");

            helper.setFrom("nevermore_sf@aliyun.com");

            helper.setTo("164449582@qq.com");

           // helper.addAttachment("Bye Bye Beautiful",new File("/home/nevermore/音乐/myMusic/nightwish/Bye Bye Beautiful.mp3"));
            helper.addAttachment("file",new File("/home/nevermore/图片/北京一卡通电子发票.pdf"));

            helper.setText(""+"/n"+ LocalDateTime.now());

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}
