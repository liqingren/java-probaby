package com.xhu.javaprobaby.pojo.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 用于查询宝宝的所有亲友
 */
@Data
@Builder
public class RealtiveVO {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 昵称
     */
    private String username;

    /**
     * 头像
     */
    private String userPhoto;

    /**
     * 亲友身份
     */
    private String identity;

    public RealtiveVO() {
    }

    public RealtiveVO(Integer userId, String username, String userPhoto, String identity) {
        this.userId = userId;
        this.username = username;
        this.userPhoto = userPhoto;
        this.identity = identity;
    }
}
