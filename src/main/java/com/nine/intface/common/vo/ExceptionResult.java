package com.nine.intface.common.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : Rubi
 * @version : 2018-10-10 12:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper =true)
public class ExceptionResult extends Result implements Serializable {
    private static final long serialVersionUID = 1L;
    public ExceptionResult(int code,String message){
        super(code,message);
    }
    public ExceptionResult(int code,Exception e){
        super(code,e.toString());
    }
}
