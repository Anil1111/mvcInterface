package com.lianhe.nine.intface.vo;

import com.github.pagehelper.PageInfo;
import com.lianhe.nine.intface.vo.Result;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Rubi
 * @version : 2018-10-12 10:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper =true)
public class JeasyResult<T> extends Result {
    private long total;
    private List<T> rows =new ArrayList<>();
    public JeasyResult(int code,String message,long total,List<T> rows){
        super(code,message);
        this.total =total;
        this.rows = rows;
    }
    public JeasyResult(int code,String message){
        super(code,message);
    }

    public JeasyResult(int code,String message,PageInfo<T> pageInfo){
        super(code,message);
        this.total =pageInfo.getTotal();
        this.rows = pageInfo.getList();
    }
    public JeasyResult(int code,String message,T t){
        super(code,message);
        this.total =0;
        this.rows.add(t);
    }

//    @Override
//    public String toString() {
//        return JSON.toJSONString(this);
//    }
}
