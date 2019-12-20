package com.lht;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class})
@MapperScan("com.lht.cDao")
@EnableTransactionManagement
public class NoWebStartApplication  {

    public static void main(String[] args) {
        SpringApplication springApplication=new SpringApplication(NoWebStartApplication.class);
        springApplication.setBannerMode(Banner.Mode.LOG);
        springApplication.run(args);

//        SpringApplication.run(NoWebStartApplication.class, args);

    }


}
