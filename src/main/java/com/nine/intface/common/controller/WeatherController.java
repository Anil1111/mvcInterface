package com.nine.intface.common.controller;

import com.google.common.collect.Maps;
import com.nine.intface.common.constants.URLConstant;
import com.nine.intface.common.service.IUserService;
import com.nine.intface.common.tasks.IAsyncTaskService;
import com.nine.intface.common.vo.Result;
import com.nine.intface.common.vo.ResultFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @author : Rubi
 * @version : 2018-10-10 16:52
 */
@Api(value = "/test", tags = {"测试用接口"})
@RestController
@RequestMapping("/test")
@SuppressWarnings("all")
public class WeatherController implements Suger {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAsyncTaskService asyncTaskService;
//
//    @ApiOperation(httpMethod = "POST", value = "根据城市名查询天气信息", tags = {"测试用接口"})
//    @ApiImplicitParam(name = "city", value = "城市", dataType = "String", paramType = "query", example = "湖州")
//    @PostMapping("/weather")
//    @ResponseBody
//    public Result getWeatherInfo(@RequestParam String city) throws Exception {
//        //get 用hashmap 填充,相当于  obj..)    post必须使用 LinkMultiValueMap
//        Map<String, Object> map = Maps.newHashMap();
//        map.put("city", city);
//        return ResultFactory.getOKRestResult(getSimple(URLConstant.ETOUCH_WEATHER, map, restTemplate));
//    }
//
//    @ApiOperation(httpMethod = "POST", value = "根据ip查询地址信息", tags = {"测试用接口"})
//    @ApiImplicitParam(name = "ip", value = "城市", dataType = "String", paramType = "query", example = "117.148.92.108")
//    @PostMapping("/ip")
//    public Result getIpAdressInfo(@RequestParam String ip) throws Exception {
//        Map<String, Object> map = Maps.newHashMap();
//        map.put("ip", ip);
//        return ResultFactory.getOKRestResult(getSimple(URLConstant.TAOBAO_IP, map, restTemplate));
//    }
//
//    @GetMapping("/hello")
//    public Result hello(){
//        return ResultFactory.getOKRestResult();
//    }



}
