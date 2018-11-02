package com.nine.intface;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Rubi
 * @since 2018-10-10
 */
@SpringBootApplication
//@EnableCaching
@Slf4j
public class RubiApplication {
    public static void main(String[] args) {
        log.info("----------------Start!");
        SpringApplication.run(RubiApplication.class, args);
    }

}

//@ImportResource(locations = {"classpath:beans.mapper"})适用于 spring配置文件
/**
 * EnableScheduling
 * file: ./config/
 * file: ./
 * classpath:/config/
 * classpath:
 * 优先级由高到低，高优先级的会覆盖低优先级的
 * 互补?
 */

