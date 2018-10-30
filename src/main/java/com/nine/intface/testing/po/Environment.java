package com.nine.intface.testing.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 环境表
 * </p>
 *
 * @author Rubi
 * @since 2018-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("test_environment")
@ApiModel(value="Environment对象", description="环境表")
public class Environment extends Model<Environment> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "项目id")
    private Integer projectId;

    @ApiModelProperty(value = "通信协议")
    private String scheme;

    @ApiModelProperty(value = "http请求类型")
    private String httpMethod;

    @ApiModelProperty(value = "ip地址或域名")
    private String host;

    @ApiModelProperty(value = "端口号")
    private Integer port;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "逻辑删除标识")
    @TableLogic
    private Integer deleteFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
