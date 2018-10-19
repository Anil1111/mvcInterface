package com.lianhe.nine.intface.po;

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
 * 项目表
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Project对象", description="项目表")
public class Project extends Model<Project> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "项目名")
    private String project_name;

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
