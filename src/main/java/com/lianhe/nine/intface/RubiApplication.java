package com.lianhe.nine.intface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * file: ./config/
 * file: ./
 * classpath:/config/
 * classpath:
 * 优先级由高到低，高优先级的会覆盖低优先级的
 * 互补?
 */
@EnableAsync
@EnableCaching//@Cacheable，@CachePut
@SpringBootApplication
@EnableScheduling

public class RubiApplication {
    private static Logger logger = LoggerFactory.getLogger(RubiApplication.class);
    public static void main(String[] args) {
        logger.info("----------------Start!");
        //If you use Thymeleaf, set spring.thymeleaf.cache to false. See ThymeleafAutoConfiguration for other Thymeleaf customization options.
        SpringApplication.run(RubiApplication.class, args);
    }

}
//@ImportResource(locations = {"classpath:beans.mapper"})适用于 spring配置文件
