package com.hnk.account.dao.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户对象
 * @author Java助手
 * @date 2020/05/25 16:04
 */
@Data
@ApiModel("用户对象")
public class User implements Serializable {

    @ApiModelProperty(value = "主键", example = "1")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty(value = "性别", example = "1")
    private Byte sex;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String mobile;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("是否删除，1是，0否")
    private Boolean isDelete;
}