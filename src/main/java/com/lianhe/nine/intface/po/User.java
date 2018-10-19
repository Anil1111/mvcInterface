package com.lianhe.nine.intface.po;

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
 * 用户表
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("developer_user")
@ApiModel(value="User对象", description="用户表")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "昵称")
    private String nick_name;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "创建人")
    private String creator_name;

    @ApiModelProperty(value = "创建时间")
    private Date create_time;

    @ApiModelProperty(value = "更新时间")
    private Date update_time;

    @ApiModelProperty(value = "更新人")
    private String updator_name;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer delete_flag;

    @ApiModelProperty(value = "禁用启用")
    private Integer disable_flag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
