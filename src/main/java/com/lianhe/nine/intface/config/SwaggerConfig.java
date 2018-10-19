package com.lianhe.nine.intface.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : Rubi
 * @version : 2018-10-15 18:56 下午
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean enableSwagger;


    public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.lianhe.nine.intface.controller";
    public static final String VERSION = "0.0.1";
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口管理接口文档")
                .description("path类型的 参数 必须 传入")
//                .license("Apache 2.0")
//                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version(VERSION)
                .contact(new Contact("Rubi", "", "henry554@21cn.com"))
                .build();
    }
    @Bean
    public Docket controllerApi() {
        Docket docket =   new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().enable(enableSwagger);
        return docket;
    }

}


