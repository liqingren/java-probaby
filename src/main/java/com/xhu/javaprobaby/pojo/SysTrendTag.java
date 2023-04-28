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
 * 动态-标签表
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysTrendTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 动态id
     */
    private Integer trendId;

    /**
     * 标签id
     */
    private Integer tagId;

    public SysTrendTag() {
    }

    public SysTrendTag(Integer id, Integer trendId, Integer tagId) {
        this.id = id;
        this.trendId = trendId;
        this.tagId = tagId;
    }
}
