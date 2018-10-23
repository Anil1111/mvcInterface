package com.nine.intface.common.vo;


import com.github.pagehelper.PageInfo;
import com.nine.intface.common.constants.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author : Rubi
 * @version : 2018-10-09 23:23 下午
 */
public class ResultFactory {
    private static Logger logger = LoggerFactory.getLogger(ResultFactory.class);

    public static void simpleLog(Result result){
        logger.info("Result :{}",result);
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
        RestResult result =new RestResult(Constant.FAILED.getIndex(), Constant.FAILED.getName(), data);
        simpleLog(result);
        return result;
    }

    public static RestResult getFailedRestResult() {
        RestResult result = new RestResult(Constant.FAILED.getIndex(), Constant.FAILED.getName());
        simpleLog(result);
        return result;
    }

    public static RestResult getWrongParamRestResult() {
        RestResult result =new RestResult(Constant.WRONG_PARAM.getIndex(), Constant.WRONG_PARAM.getName());
        simpleLog(result);
        return result;
    }
    public static ExceptionResult getErrorResult(){
        ExceptionResult result =new ExceptionResult(Constant.URL_EXCEPTION.getIndex(),Constant.URL_EXCEPTION.getName());
        simpleLog(result);
        return result;
    }

    public static ExceptionResult getExceptionResult(int code, String message){
        ExceptionResult result =new ExceptionResult(code, message);
        simpleLog(result);
        return result;
    }
    public static ExceptionResult getOtherExceptionResult(Exception e){
        ExceptionResult result =new ExceptionResult(Constant.EXCEPTION_OTHER.getIndex(),e+e.getMessage());
        simpleLog(result);
        return result;
    }

    public static <T> JeasyResult getJeasySult(int code, String message, PageInfo<T> pageInfo){
        JeasyResult result =new JeasyResult<>(code,message,pageInfo);
        simpleLog(result);
        return result;
    }
    public static <T> JeasyResult getOKJeasySult(){
        JeasyResult result =new JeasyResult<>(Constant.SUCCESS.getIndex(), Constant.SUCCESS.getName());
        simpleLog(result);
        return result;
    }
    public static <T> JeasyResult getFailedJeasySult(){
        JeasyResult result =new JeasyResult<>(Constant.FAILED.getIndex(), Constant.FAILED.getName());
        simpleLog(result);
        return result;
    }
    public static <T> JeasyResult getFailedJeasySult(T t){
        JeasyResult result =new JeasyResult<>(Constant.FAILED.getIndex(), Constant.FAILED.getName(),t);
        simpleLog(result);
        return result;
    }
    public static <T> JeasyResult getJeasySult(int code, String message, long total, List<T> rows){
        JeasyResult result =new JeasyResult<>(code,message,total,rows);
        simpleLog(result);
        return result;
    }

    public static <T> JeasyResult getOKJeasySult(PageInfo<T> pageInfo) {
        JeasyResult result =new JeasyResult<>(Constant.SUCCESS.getIndex(), Constant.SUCCESS.getName(),pageInfo);
        simpleLog(result);
        return result;
    }
    public static <T> JeasyResult getOKJeasySult(T t) {
        JeasyResult result =new JeasyResult<>(Constant.SUCCESS.getIndex(), Constant.SUCCESS.getName(),t);
        simpleLog(result);
        return result;
    }

    public static ExceptionResult getExceptionInfo(Constant c){
        ExceptionResult result =ResultFactory.getExceptionResult(c.getIndex(),c.getName());
        return result;
    }
   

}
