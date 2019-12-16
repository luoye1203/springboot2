package com.lht.myquartz.eConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket helloApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(helloApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lht.myquartz.aController"))
                .paths(PathSelectors.any())
                .build();

    }
    private ApiInfo helloApiInfo() {
        return new ApiInfoBuilder()
                .title("quartz_test")//大标题
                .description("quartz_测试")//详细描述
                .version("1.0")//版本
                .termsOfServiceUrl("--")
                .license("--")
                .build();
    }

}
