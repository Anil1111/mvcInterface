package com.nine.intface.common.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nine.intface.common.constants.URLConstant;
import com.nine.intface.common.filter.LogFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

/**
 * 声明是 配置类 ，替代spring配置文件@EnableWebMvc全面接管spring mvc
 * @author : Rubi
 * @version : 2018-10-09 10:43
 */
@Slf4j
@Configuration
public class MvcConfig implements WebMvcConfigurer {


//    @Bean
//    public FilterRegistrationBean filterRegistrationBean(){
//        log.info("----------------ConfigInit:FilterRegistrationBean");
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(getLogFilter());
//        Map<String,String> excludeUrlMap = Maps.newHashMap();
//        excludeUrlMap.put(URLConstant.SUFFIXPATTONS,"");
//        filterRegistrationBean.setInitParameters(excludeUrlMap);
//        filterRegistrationBean.setOrder(1);
//        filterRegistrationBean.setUrlPatterns(Lists.newArrayList("/*"));
//
//        return filterRegistrationBean;
//    }

//    @Bean
//    public LogFilter getLogFilter(){
//        return new LogFilter();
//    }
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        //资源请求交给数据库记录/index.html"
//        registry.addInterceptor(getRequestInterceptor())
//                .addPathPatterns("/**","/")
//                .excludePathPatterns(
//                        "/index/**","/test_fold/**",
//                        "/druid/",
//                        "/druid/**",
//                        "/swagger-resources/**",
//                        "/swagger-resources/",
//                        "/error",
//                        "/swagger-ui.html",
//                        "/webjars/springfox-swagger-ui/**",
//                        "/my/**",
//                        "/null",
//                        "/null/**"
//                );
//
//        registry.addInterceptor(getSystemLogInterceptor())
//                .addPathPatterns("/**","/")
//                .excludePathPatterns("/index/**");
//    }
//    /**
//     * 因为本身体注入了service,所以这么注入进容器
//     * @return SystemLogInterceptor
//     */
//    @Bean
//    public SystemLogInterceptor getSystemLogInterceptor() {
//        log.info("----------------ConfigInit:SystemLogInterceptor");
//        return new SystemLogInterceptor();
//    }
//    /**
//     * 因为本身体注入了service,所以这么注入进容器
//     * @return RequestInterceptor
//     */
//    @Bean
//    public RequestInterceptor getRequestInterceptor() {
//        log.info("----------------ConfigInit:RequestInterceptor");
//        return new RequestInterceptor();
//    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        log.info("----------------ConfigInit:DateFormatters");
        registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("----------------ConfigInit:CorsMappings");
        registry.addMapping("/**")
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedOrigins(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .allowCredentials(true);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("----------------ConfigInit:ResourceHandlers");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/","file:static/");

    }

    //    @Bean
//    public HelloService helloService(){
//       log.info("create a bean named helloService");
//        return new HelloService();
//    }


//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/hello").setViewName("success");
//    }


    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

    }
}
