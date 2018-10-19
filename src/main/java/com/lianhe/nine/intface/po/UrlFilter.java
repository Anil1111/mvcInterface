package com.lianhe.nine.intface.po;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2018-10-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("url_filter")
@ApiModel(value="UrlFilter对象", description="shiro过滤链表")
public class UrlFilter extends Model<UrlFilter> implements Comparable<UrlFilter>{
    /* implements Comparator<UrlFilter> */
    /*disable_flag*/
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "uri路径")
    private String uri;

    @ApiModelProperty(value = "过滤器名")
    private String filter;

    @ApiModelProperty(value = "父排序id")
    private Integer parent_sort;

    @ApiModelProperty(value = "子排序id")
    private Integer sub_sort;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date create_time;

    @ApiModelProperty(value = "更新时间")
    private Date update_time;

    @ApiModelProperty(value = "创建人")
    private String creator_name;

    @ApiModelProperty(value = "更新人")
    private String updator_name;

    @ApiModelProperty(value = "禁用启用")
    private Integer disable_flag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public int compareTo(UrlFilter o) {
        int superResult = parent_sort.compareTo(o.getParent_sort());
        int subResult = sub_sort.compareTo(o.getSub_sort());
        return superResult != 0 ? superResult : subResult;
    }


}
