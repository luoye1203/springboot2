package com.lht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class})
public class MongodbrStartApplication  {

    public static void main(String[] args) {
        SpringApplication.run(MongodbrStartApplication.class, args);

    }

}
