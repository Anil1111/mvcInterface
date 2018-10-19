package com.lianhe.nine.intface;

import com.lianhe.nine.intface.config.RestTemplateConfig;
import com.lianhe.nine.intface.service.IUrlFilterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private IUrlFilterService urlFilterService;
    @Autowired
    private RestTemplate restTemplate;
    @Test
    public void contextLoads() throws Exception{
        String json = restTemplate.getForObject("http://wthrcdn.etouch.cn/weather_mini?city="+"湖州", String.class);

        System.out.println(json);
    }

}
