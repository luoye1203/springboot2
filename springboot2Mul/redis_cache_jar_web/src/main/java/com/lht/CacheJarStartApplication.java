package com.lht;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class})
@EnableSwagger2
//@EnableScheduling
//@EnableCaching
@ServletComponentScan //使@WebFilter 起作用
@EnableCaching
@MapperScan("com.lht.cDao")
public class CacheJarStartApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CacheJarStartApplication.class, args);

    }

}
