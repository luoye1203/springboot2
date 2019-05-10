package com.lht;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.annotation.WebFilter;

@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
@EnableSwagger2
@EnableScheduling
@EnableCaching
@ServletComponentScan //使@WebFilter 起作用
@MapperScan("com.lht.cDao")
public class AopStartApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AopStartApplication.class, args);

    }

}
