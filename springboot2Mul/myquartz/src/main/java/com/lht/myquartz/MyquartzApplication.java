package com.lht.myquartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class})
@EnableSwagger2
@MapperScan("com.lht.myquartz.cDao")
public class MyquartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyquartzApplication.class, args);
    }

}
