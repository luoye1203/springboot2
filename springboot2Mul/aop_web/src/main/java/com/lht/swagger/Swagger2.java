package com.lht.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
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

    @Bean
    public Docket test() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("TestSwagger")
                .genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/swagger/.*"))//过滤的接口
                .build()
                .globalOperationParameters(getTokenParam())
                .apiInfo(detailInfo("TestSwagger"));
    }

    @Bean
    public Docket springsession() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("springsession")
                .genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/springsession/.*"))//过滤的接口
                .build()
                .globalOperationParameters(getTokenParam())
                .apiInfo(detailInfo("springsession"));
    }





    private ApiInfo detailInfo(String title) {
        return new ApiInfoBuilder()
                .title(title)//大标题
                .build();
    }
}
