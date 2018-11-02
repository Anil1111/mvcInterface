package com.nine.intface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.nine.intface.common.constants.ActionConstant;
import com.nine.intface.common.dao.UserMapper;
import com.nine.intface.common.service.IUrlFilterService;
import com.nine.intface.common.tasks.IAsyncTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpMethod.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("all")
public class DemoApplicationTests {

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

    }




}
