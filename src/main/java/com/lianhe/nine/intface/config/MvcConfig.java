package com.lianhe.nine.intface.config;

import com.lianhe.nine.intface.interceptpr.RequestInterceptor;
import com.lianhe.nine.intface.interceptpr.SystemLogInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : Rubi
 * @version : 2018-10-09 10:43
 */

@Configuration//声明是 配置类 ，替代spring配置文件//@EnableWebMvc//全面接管spring mvc
public class MvcConfig implements WebMvcConfigurer {
    // private Logger logger =
    private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);
    


    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        registry.addInterceptor(getRequestInterceptor())
                .addPathPatterns("/**","/")
                .excludePathPatterns(
                        "/index/**","/index.html","/test_fold/**",//资源请求交给数据库记录
                        "/druid/",
                        "/druid/**",
                        "/swagger-resources/**",
                        "/swagger-resources/",
                        "/error",
                        "/swagger-ui.html",
                        "/webjars/springfox-swagger-ui/**",
                        "/swagger-ui.html",
                        "/null",
                        "/null/**"
                );

        registry.addInterceptor(getSystemLogInterceptor()).addPathPatterns("/**").addPathPatterns("/").excludePathPatterns("/index/**");
    }

    /**
     * 因为本身体注入了service,所以这么注入进容器
     * @return RequestInterceptor
     */
    @Bean
    public RequestInterceptor getRequestInterceptor() {
        logger.info("----------------ConfigInit:RequestInterceptor");
        return new RequestInterceptor();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        logger.info("----------------ConfigInit:DateFormatter");
        registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .allowCredentials(true);
    }

    /**
     * 因为本身体注入了service,所以这么注入进容器
     * @return SystemLogInterceptor
     */
    @Bean
    public HandlerInterceptor getSystemLogInterceptor() {
        logger.info("----------------ConfigInit:HandlerInterceptor");
        return new SystemLogInterceptor();
    }


    //    @Bean
//    public HelloService helloService(){
//       logger.info("create a bean named helloService");
//        return new HelloService();
//    }


//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/hello").setViewName("success");
//    }
}
