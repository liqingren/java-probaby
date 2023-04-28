package com.xhu.javaprobaby.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ren
 * @since 2023-03-10
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysIdentity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "identity_id", type = IdType.AUTO)
    private Integer identityId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 宝宝id
     */
    private Integer babyId;

    /**
     * 用户是宝宝的什么家长（爸爸、妈妈）
     */
    private String identity;

    public SysIdentity() {
    }

    public SysIdentity(Integer identityId, Integer userId, Integer babyId, String identity) {
        this.identityId = identityId;
        this.userId = userId;
        this.babyId = babyId;
        this.identity = identity;
    }
}
