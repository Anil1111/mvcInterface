package com.lianhe.nine.intface.constant;

/**
 * @author : Rubi
 * @version : 2018-10-08 14:31
 */

public enum Constant {

    SHIRO_LOGIN_FAILURE("shiroLoginFailure", -111),
    //PAGE_SIZE = 10;

    SUCCESS("操作成功", 1),
    FAILED("操作失败", -1),
    WRONG_PARAM("参数有误", -99),
    URL_EXCEPTION("url有误", -10000),
    PAGE_SIZE("分页",10),
    EXCEPTION_SQLEXCEPTION("参数获取异常", -100),
    EXCEPTION_NULLPARAMETER("参数获取异常", -101),
    EXCEPTION_NULLPOINTER("空指针异常", -102),
    EXCEPTION_NUMBERFORMAT("数字格式化异常", -103),
    EXCEPTION_INDEXOUTOFBOUNDS("数组越界", -104),
    EXCEPTION_UNAUTHENTICATED("请求没有访问权限", -177),
    EXCEPTION_HOSTUNAUTHORIZED("权限异常", -178),
    EXCEPTION_UNAUTHORIZED("权限/认证异常", -179),
    EXCEPTION_LOCKEDACCOUNT("账号被锁定", -180),
    EXCEPTION_INCORRECTCREDENTIALS("错误的凭据异常", -181),
    EXCEPTION_UNKNOWNACCOUNT("未知账户", -182),
    EXCEPTION_EXCESSIVEATTEMPTS("登录重试次数超限", -183),
    EXCEPTION_EXPIREDCREDENTIALS("过期的凭据异常", -184),
    EXCEPTION_CONCURRENTACCESS("并发访问异常", -185),
    EXCEPTION_DISABLEDACCOUNT("用户禁用", -186),
    EXCEPTION_UNSUPPORTEDTOKEN("使用了不支持的token", -187),
    EXCEPTION_ACCOUNT("账号异常", -188),
    EXCEPTION_CREDENTIALS("凭证异常", -189),
    EXCEPTION_AUTHENTICATION("凭据异常,请重新登陆", -190),
    EXCEPTION_AUTHORIZATION("授权异常", -191),
    EXCEPTION_METHODARGUMENTTYPEMISMATCH("请求有误", -192),
    EXCEPTION_MISSINGSERVLETREQUESTPARAMETER("参数缺少", -193),
    EXCEPTION_DUPLICATEKEYEXCEPTION("有重复参数,无法插入",-194),
    EXCEPTION_DATAINTEGRITYVIOLATION("参数异常",-195),
    EXCEPTION_BIND("参数格式不一致",-196),
    EXCEPTION_BADSQLGRAMMAREXCEPTION("参数异常",-197),
//    EXCEPTION_TEMPLATEENGINEEXCEPTION("模版引擎异常",-500),

    EXCEPTION_RUNTIME("Runtime Exception!", -1999),
    EXCEPTION_OTHER("Unknown Exception!", -2000);


    private String name;
    private int index;

     Constant(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getMsg(int index) {
        for (Constant c : Constant.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}

