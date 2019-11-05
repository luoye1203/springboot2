package com.lht.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by li on 2017/7/4.
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    //添加全局参数
    private List<Parameter> getTokenParam(){
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }

    //携带全局参数

    @Bean
    public Docket globalPrametersRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("私有服务")
                .apiInfo(globalPrametersApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lht.aController"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(getTokenParam());
    }

    //不携带全局参数

    @Bean
    public Docket noGlobalPrametersRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("公共服务")
                .apiInfo(noGlobalPrametersApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lht.bController"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo globalPrametersApiInfo() {
        return new ApiInfoBuilder()
                .title("测试 APIs携带全局参数")
                .description("测试api接口文档，携带全局参数")
//                .termsOfServiceUrl("http://www.baidu.com")
                .version("1.0")
                .build();
    }

    private ApiInfo noGlobalPrametersApiInfo() {
        return new ApiInfoBuilder()
                .title("测试 APIs不携带全局参数")
                .description("测试api接口文档，不携带全局参数")
//                .termsOfServiceUrl("http://www.baidu.com")
                .version("1.0")
                .build();
    }




}
