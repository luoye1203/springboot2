package com.lht;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
//@Import(DynamicDataSourceRegister.class)
@EnableSwagger2
@EnableScheduling
@MapperScan("com.lht.cDao")
public class InterceptorStartApplication extends SpringBootServletInitializer {



//    @Bean
//    public DynamicDataSourceAnnotationAdvisor dynamicDatasourceAnnotationAdvisor() {
//        return new DynamicDataSourceAnnotationAdvisor(new DynamicDataSourceAnnotationInterceptor());
//    }


    public static void main(String[] args) {
        SpringApplication.run(InterceptorStartApplication.class, args);

    }

}
