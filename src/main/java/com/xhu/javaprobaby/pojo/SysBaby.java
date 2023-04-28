package com.xhu.javaprobaby.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;

import java.sql.Time;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 宝宝信息表
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysBaby implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 宝宝id
     */
    @TableId(value = "baby_id", type = IdType.AUTO)
    private Integer babyId;

    /**
     * 创建宝宝的用户id
     */
    private Integer userId;

    /**
     * 宝宝小名
     */
    private String nickname;

    /**
     * 性别
     */
    private Boolean babySex;

    /**
     * 头像
     */
    private String babyPhoto;

    /**
     * 生日
     */
    @JsonFormat(locale="zh", timezone="GMT+8",pattern = "yyyy-MM-dd")
    private Date babyBirth;

    /**
     * 血型
     */
    private String babyBlood;

    /**
     * 出生时刻
     */
    @JsonFormat(locale="zh", timezone="GMT+8",pattern = "HH:mm")
    private Date babyMoment;

    /**
     * 身高
     */
    private BigDecimal height;

    /**
     * 体重
     */
    private BigDecimal weight;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date modifiedTime;

    public SysBaby() {
    }

    public SysBaby(Integer babyId, Integer userId, String nickname, Boolean babySex, String babyPhoto, Date babyBirth, String babyBlood, Date babyMoment, BigDecimal height, BigDecimal weight, Date createTime, Date modifiedTime) {
        this.babyId = babyId;
        this.userId = userId;
        this.nickname = nickname;
        this.babySex = babySex;
        this.babyPhoto = babyPhoto;
        this.babyBirth = babyBirth;
        this.babyBlood = babyBlood;
        this.babyMoment = babyMoment;
        this.height = height;
        this.weight = weight;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }
}
