package com.yonyougov.gateway.entity.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/3/24
 */
@ApiModel(value = "com.yonyougov.portal.engine.entity.ApMenu")
@Data
public class ApMenu implements Serializable {
    @ApiModelProperty(value = "id")
    @Column(length = 64)
    private String id;

    @ApiModelProperty(value = "审计")
    @Column(length = 1)
    private String audi;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "是否符合菜单")
    private Boolean composit;

    @ApiModelProperty(value = "性能监控")
    @Column(length = 1)
    private String contr;

    @ApiModelProperty(value = "创建人")
    private String creator;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "删除标识")
    private String dr;

    @ApiModelProperty(value = "是否启用")
    private Short enabled;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "字体颜色")
    private String iconColor;

    @ApiModelProperty(value = "图片base64加密密文")
    private String img;

    @ApiModelProperty(value = "是否叶子节点")
    private Short leafNode;

    @ApiModelProperty(value = "菜单 id")
    private String menuId;

    @ApiModelProperty(value = "菜单级次01234")
    private String menuLevel;

    @ApiModelProperty(value = "菜单状态,0正常1新菜单2热门菜单")
    private String menuStatus;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "打开状态0打开新页签1框架打开")
    private Short openStatus;

    @ApiModelProperty(value = "排序角标")
    private String orderNum;

    @ApiModelProperty(value = "父节点id")
    private String parentId;

    @ApiModelProperty(value = "系统id")
    private String sysId;

    @ApiModelProperty(value = "时间戳")
    private Date ts;

    @ApiModelProperty(value = "菜单链接")
    private String url;

    @ApiModelProperty(value = "平台菜单id")
    private String xFunctionId;

    private static final long serialVersionUID = 1L;
}