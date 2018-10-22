//package com.lianhe.nine.intface.config;
//
//import nz.net.ultraq.thymeleaf.LayoutDialect;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
//import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
//
///**
// * @author : Rubi
// * @version : 2018-10-19 21:11 下午
// */
//@Configuration
//public class TamplateEngineConfig {
//
//
//    @Bean
//    public TemplateEngine templateEngine() {
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setTemplateResolver(getResolver());
//        engine.setEnableSpringELCompiler(true);
//        engine.addDialect(new LayoutDialect());
//        return engine;
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.thymeleaf")
//    public SpringResourceTemplateResolver getResolver(){
//        SpringResourceTemplateResolver resolver= new SpringResourceTemplateResolver();
//       resolver.setCharacterEncoding("UTF-8");
//       return resolver;
//    }
//}
//
