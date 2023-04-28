package com.xhu.javaprobaby.service;

import com.xhu.javaprobaby.pojo.SysTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 大事记(标签)表 服务类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
public interface SysTagService extends IService<SysTag> {


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
