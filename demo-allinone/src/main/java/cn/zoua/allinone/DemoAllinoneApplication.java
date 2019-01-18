package cn.zoua.allinone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * springboot整合
 * mybatis、cache、thymeleaf、web、devtools、lombok、swagger、amqp、actuator、elasticsearch、redis、mail、security
 */
@EnableRabbit
@EnableCaching
@MapperScan(value = "cn.zoua.allinone.mapper")
@SpringBootApplication
public class DemoAllinoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAllinoneApplication.class, args);
    }

}

