package com.xhu.javaprobaby.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class BabyVO {
    /**
     * 宝宝id
     */
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
    private String babyMoment;

    /**
     * 身高
     */
    private BigDecimal height;

    /**
     * 体重
     */
    private BigDecimal weight;




    public BabyVO() {
    }

    public BabyVO(Integer babyId, Integer userId, String nickname, Boolean babySex, String babyPhoto, Date babyBirth, String babyBlood, String babyMoment, BigDecimal height, BigDecimal weight) {
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
    }
}
