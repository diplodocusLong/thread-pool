package com.lianglong.threadpool.demo.config;


import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lianglong
 * @date 19-7-25
 */

@Configuration
public class LocalDateTimeSerializerConfig {

    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern;

    //序列化器
    public LocalDateTimeSerializer localDateTimeserializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));
    }

    //反序列化器
    public LocalDateTimeDeserializer localDateTimeDeserializer(){

        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(pattern));
    }
    //加入spring
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeserializer())
                          .deserializerByType(LocalDateTime.class,localDateTimeDeserializer());

    }

}
