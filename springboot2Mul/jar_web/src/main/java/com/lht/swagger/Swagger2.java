package com.lht.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
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

//    @Bean
//    public Docket swaggertest() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("TestSwagger")
//                .genericModelSubstitutes(DeferredResult.class)
////                .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(true)
//                .pathMapping("/")
//                .select()
//                .paths(PathSelectors.regex("/swagger/.*"))//过滤的接口
//                .build()
//                .globalOperationParameters(getTokenParam())
//                .apiInfo(detailInfo("TestSwagger"));
//    }
//
//    @Bean
//    public Docket loginmange() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("登录管理")
//                .genericModelSubstitutes(DeferredResult.class)
////                .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(true)
//                .pathMapping("/")
//                .select()
//                .paths(PathSelectors.regex("/loginmange/.*"))//过滤的接口
//                .build()
////                .globalOperationParameters(getTokenParam())
//                .apiInfo(detailInfo("登录管理"));
//    }
//
//    @Bean
//    public Docket authtest() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("token测试")
//                .genericModelSubstitutes(DeferredResult.class)
////                .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(true)
//                .pathMapping("/")
//                .select()
//                .paths(PathSelectors.regex("/tokentest/.*"))//过滤的接口
//                .build()
//                .globalOperationParameters(getTokenParam())
//                .apiInfo(detailInfo("token测试"));
//    }
//
//    @Bean
//    public Docket jdbcService() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("jdbc模拟服务")
//                .genericModelSubstitutes(DeferredResult.class)
////                .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(true)
//                .pathMapping("/")
//                .select()
//                .paths(PathSelectors.regex("/jdbcService/.*"))//过滤的接口
//                .build()
//                .globalOperationParameters(getTokenParam())
//                .apiInfo(detailInfo("jdbc模拟服务"));
//    }
//    @Bean
//    public Docket dmjdbcService() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("dmjdbc模拟服务")
//                .genericModelSubstitutes(DeferredResult.class)
////                .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(true)
//                .pathMapping("/")
//                .select()
//                .paths(PathSelectors.regex("/dmJdbcService/.*"))//过滤的接口
//                .build()
//                .globalOperationParameters(getTokenParam())
//                .apiInfo(detailInfo("dmjdbc模拟服务"));
//    }
//
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lht.aController"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试 APIs")
                .description("测试api接口文档")
                .termsOfServiceUrl("http://www.baidu.com")
                .version("1.0")
                .build();
    }




    private ApiInfo detailInfo(String title) {
        return new ApiInfoBuilder()
                .title(title)//大标题
                .build();
    }
}
