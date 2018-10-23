package com.lianhe.nine.intface;

import com.lianhe.nine.intface.dao.UserMapper;
import com.lianhe.nine.intface.service.IUrlFilterService;
import com.lianhe.nine.intface.tasks.IAsyncTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("all")
public class DemoApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUrlFilterService urlFilterService;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IAsyncTaskService asyncTaskService;
    @Test
    public void contextLoads() throws Exception{
        String json = restTemplate.getForObject("http://wthrcdn.etouch.cn/weather_mini?city="+"湖州", String.class);

        System.out.println(json);
    }
    @Test
    public void 测试查询(){
        System.out.println(userMapper.selectObjs(null));
    }
//    @Test
//    public void 测试异步()throws Exception{
//        asyncTaskService.test1();
//        asyncTaskService.test2();
//        try {
//            logger.info("begin to deal other Task!");
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
