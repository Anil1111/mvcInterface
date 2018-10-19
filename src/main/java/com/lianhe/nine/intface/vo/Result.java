package com.lianhe.nine.intface.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Rubi
 * @version : 2018-10-10 10:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Result {
    private int code;
    private String message;

//    @Override
//    public String toString() {
//        return JSON.toJSONString(this);
//    }
}
