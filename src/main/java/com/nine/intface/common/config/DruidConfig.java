package com.nine.intface.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author : Rubi
 * @version : 2018-10-09 16:36
 */
@Slf4j
@Configuration
public class DruidConfig {

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        log.info("----------------ConfigInit:dataSource");
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean statServlet(){
        log.info("----------------ConfigInit:ServletRegistrationBean");
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> initMap = Maps.newHashMap();
        initMap.put("loginUsername","rubi");
        initMap.put("loginPassword","2333");
        initMap.put("allow","");//默认允许所有  //localhost
        bean.setInitParameters(initMap);
       return bean;
    }



    @Bean
    public FilterRegistrationBean statFilter(){
        log.info("----------------ConfigInit:FilterRegistrationBean");

        //创建过滤器
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        //设置过滤器过滤路径
        bean.addUrlPatterns("/*");
        //忽略过滤的形式
        bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return bean;
    }


}
