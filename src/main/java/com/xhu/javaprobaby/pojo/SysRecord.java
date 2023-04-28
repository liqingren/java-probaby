package com.xhu.javaprobaby.pojo;

import java.math.BigDecimal;
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
 * 成长记录表
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录id
     */
    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;

    /**
     * 宝宝id
     */
    private Integer babyId;

    /**
     * 身高
     */
    private BigDecimal height;

    /**
     * 体重
     */
    private BigDecimal weight;

    /**
     * 头围
     */
    private BigDecimal head;

    /**
     * 记录时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8",pattern = "yyyy-MM-dd")
    private Date recordTime;

    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8",pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    public SysRecord() {
    }

    public SysRecord(Integer recordId, Integer babyId, BigDecimal height, BigDecimal weight, BigDecimal head, Date recordTime, Date createTime) {
        this.recordId = recordId;
        this.babyId = babyId;
        this.height = height;
        this.weight = weight;
        this.head = head;
        this.recordTime = recordTime;
        this.createTime = createTime;
    }
}
