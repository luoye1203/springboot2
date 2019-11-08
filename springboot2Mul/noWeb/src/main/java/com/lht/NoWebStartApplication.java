package com.lht;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class})
@MapperScan("com.lht.cDao")
public class NoWebStartApplication  {

    public static void main(String[] args) {
        SpringApplication.run(NoWebStartApplication.class, args);

    }


}
