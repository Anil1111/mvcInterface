package com.lianhe.nine.intface.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 访问日志表
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_log")
@ApiModel(value="SysLog对象", description="访问日志表")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "访问时间")
    private Date create_time;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "访问路径")
    private String operate_url;

    @ApiModelProperty(value = "所用浏览器信息")
    private String operate_by;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
