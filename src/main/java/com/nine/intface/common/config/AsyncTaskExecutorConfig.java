package com.nine.intface.common.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 线程池配置、启用异步
 * @author : Rubi
 * @version : 2018-10-09 10:50
 */

//@EnableAsync(proxyTargetClass = true)
//@ComponentScan("com.nine.intface.common.tasks")
@Slf4j
@Configuration
@EnableAsync
public class AsyncTaskExecutorConfig {


    @Bean
    public Executor logExecutor() {
        log.info("----------------ConfigInit:logExecutor");
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setBeanName("logExecutor");
        //核心线程数
        taskExecutor.setCorePoolSize(50);
        //前缀
        taskExecutor.setThreadNamePrefix("logExecutor-");
        //最大线程数
        taskExecutor.setMaxPoolSize(100);
        //线程池所使用的缓冲队列
        taskExecutor.setQueueCapacity(100);
        taskExecutor.initialize();
        return taskExecutor;
    }

}
