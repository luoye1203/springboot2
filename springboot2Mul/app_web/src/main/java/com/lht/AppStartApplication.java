package com.lht;

import com.lht.eAop.DynamicDataSourceAnnotationAdvisor;
import com.lht.eAop.DynamicDataSourceAnnotationInterceptor;
import com.lht.register.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
@Import(DynamicDataSourceRegister.class)
@EnableSwagger2
@EnableScheduling
@MapperScan("com.lht.cDao")
public class AppStartApplication extends SpringBootServletInitializer {

    @Bean
    public DynamicDataSourceAnnotationAdvisor dynamicDatasourceAnnotationAdvisor() {
        return new DynamicDataSourceAnnotationAdvisor(new DynamicDataSourceAnnotationInterceptor());
    }


    public static void main(String[] args) {
        SpringApplication.run(AppStartApplication.class, args);

    }

}
