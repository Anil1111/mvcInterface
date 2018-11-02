package com.nine.intface.common.vo;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nine.intface.common.constants.Constant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author : Rubi
 * @version : 2018-10-09 23:23 下午
 */
@Slf4j
public class ResultFactory {

    public static void simpleLog(Result result) {
        log.info("result :{}", result);
    }

    public static void pageLog(RestResult result) {
        log.info("result :{}", result);
        log.info("record :{}", ((Page) result.getData()).getRecords());
        log.info("current :{},total :{},size :{}",
                ((Page) result.getData()).getCurrent(),
                ((Page) result.getData()).getTotal(),
                ((Page) result.getData()).getSize());
    }

    public static RestResult getRestResult(int code, String message) {

        RestResult result = new RestResult(code, message);
        simpleLog(result);
        return result;
    }

    public static RestResult getRestResult(int code, String message, Object data) {
        RestResult result = new RestResult(code, message, data);
        simpleLog(result);
        return result;
    }

    public static <T> RestResult getOKRestResult(IPage<T> page) {
        RestResult result = new RestResult(Constant.SUCCESS.getIndex(), Constant.SUCCESS.getName(), page);
        pageLog(result);
        return result;
    }

    public static RestResult getOKRestResult(Object data) {
        RestResult result = new RestResult(Constant.SUCCESS.getIndex(), Constant.SUCCESS.getName(), data);
        simpleLog(result);
        return result;
    }


    public static RestResult getOKRestResult() {
        RestResult result = new RestResult(Constant.SUCCESS.getIndex(), Constant.SUCCESS.getName());
        simpleLog(result);
        return result;
    }

    public static RestResult getFailedRestResult(Object data) {
        RestResult result = new RestResult(Constant.FAILED.getIndex(), Constant.FAILED.getName(), data);
        simpleLog(result);
        return result;
    }

    public static RestResult getFailedRestResult() {
        RestResult result = new RestResult(Constant.FAILED.getIndex(), Constant.FAILED.getName());
        simpleLog(result);
        return result;
    }

    public static RestResult getWrongParamRestResult() {
        RestResult result = new RestResult(Constant.WRONG_PARAM.getIndex(), Constant.WRONG_PARAM.getName());
        simpleLog(result);
        return result;
    }

    public static ExceptionResult getErrorResult() {
        ExceptionResult result = new ExceptionResult(Constant.URL_EXCEPTION.getIndex(), Constant.URL_EXCEPTION.getName());
        simpleLog(result);
        return result;
    }

    public static ExceptionResult getExceptionResult(int code, String message) {
        ExceptionResult result = new ExceptionResult(code, message);
        simpleLog(result);
        return result;
    }

    public static ExceptionResult getOtherExceptionResult(Exception e) {
        ExceptionResult result = new ExceptionResult(Constant.EXCEPTION_OTHER.getIndex(), e);
        simpleLog(result);
        return result;
    }


    public static ExceptionResult getExceptionInfo(Constant c) {
        ExceptionResult result = ResultFactory.getExceptionResult(c.getIndex(), c.getName());
        return result;
    }


}
