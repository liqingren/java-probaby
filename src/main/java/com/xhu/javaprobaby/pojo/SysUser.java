package com.xhu.javaprobaby.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 微信小程序登录唯一标识
     */
    private String openId;

    /**
     * 昵称
     */
    private String username;

    /**
     * 头像
     */
    private String userPhoto;

    /**
     * 性别
     */
    private Boolean userSex;

    /**
     * 出生日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8",pattern = "yyyy-MM-dd")
    private Date userBirth;

    /**
     * 地区（第一个数：省，-，第二个数：市）
     */
    private String userCity;

    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8",pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date modifiedTime;

    public SysUser() {
    }

    public SysUser(Integer userId, String phone, String password, String openId, String username, String userPhoto, Boolean userSex, Date userBirth, String userCity, Date createTime, Date modifiedTime) {
        this.userId = userId;
        this.phone = phone;
        this.password = password;
        this.openId = openId;
        this.username = username;
        this.userPhoto = userPhoto;
        this.userSex = userSex;
        this.userBirth = userBirth;
        this.userCity = userCity;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }
}
