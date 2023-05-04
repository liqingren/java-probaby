package com.xhu.javaprobaby.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author ren
 * @since 2023-05-04
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRegion implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父id
     */
    private Integer pid;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 全名
     */
    private String fullname;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 首字母
     */
    private String firstLetter;

    /**
     * 首字母全拼
     */
    private String fullLetter;

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 经度
     */
    private BigDecimal lng;

    /**
     * 层级
     */
    private Boolean level;


    /**
     * 子级
     */
    List<SysRegion> children;


    public SysRegion() {
        children = new ArrayList<>();
    }

    public SysRegion(Integer id, Integer pid, String code, String name, String fullname, String pinyin, String firstLetter, String fullLetter, BigDecimal lat, BigDecimal lng, Boolean level, List<SysRegion> children) {
        this.id = id;
        this.pid = pid;
        this.code = code;
        this.name = name;
        this.fullname = fullname;
        this.pinyin = pinyin;
        this.firstLetter = firstLetter;
        this.fullLetter = fullLetter;
        this.lat = lat;
        this.lng = lng;
        this.level = level;
        this.children = children;
    }
}
