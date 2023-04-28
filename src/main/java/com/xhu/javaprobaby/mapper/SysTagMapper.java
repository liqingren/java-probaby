package com.xhu.javaprobaby.mapper;

import com.xhu.javaprobaby.pojo.SysTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 大事记(标签)表 Mapper 接口
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
public interface SysTagMapper extends BaseMapper<SysTag> {

    /**
     * 获取所有标签
     * @return
     */
    List<SysTag> listTag();


    /**
     * 根据标签内容获取标签
     * @param content
     * @return
     */
    SysTag getTag(String content);

    /**
     * 根据动态标签获取标签信息
     * @param trendId
     * @return
     */
    List<SysTag> listTags(Integer trendId);
}
