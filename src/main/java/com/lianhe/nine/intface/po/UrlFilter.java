package com.lianhe.nine.intface.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Comparator;
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
 * shiro过滤链表
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UrlFilter对象", description="shiro过滤链表")
public class UrlFilter extends Model<UrlFilter> implements Comparable<UrlFilter> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "uri路径")
    private String uri;

    @ApiModelProperty(value = "过滤器名")
    private String filter;

    @ApiModelProperty(value = "父排序id")
    private Integer parentSort;

    @ApiModelProperty(value = "子排序id")
    private Integer subSort;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建人")
    private String creatorName;

    @ApiModelProperty(value = "更新人")
    private String updatorName;

    @ApiModelProperty(value = "禁用启用")
    private Integer disableFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public int compareTo(UrlFilter o) {
        int superResult = parentSort.compareTo(o.getParentSort());
        int subResult = subSort.compareTo(o.getSubSort());
        return superResult != 0 ? superResult : subResult;
    }


}




