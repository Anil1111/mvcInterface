package com.lianhe.nine.intface.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;


/**
 * @author : Rubi
 * @version : 2018-10-09 23:11 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper =true)
public class RestResult extends Result implements Serializable{
    private static final long serialVersionUID = 1L;
    private Object data;

    public RestResult(int code,String message,Object data){
        super(code,message);
        this.data=data;
    }
    public RestResult(int code,String message){
        super(code,message);
    }

//    @Override
//    public String toString() {
//        return JSON.toJSONString(this);
//    }
}
