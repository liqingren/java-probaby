package com.xhu.javaprobaby.mapper;

import com.xhu.javaprobaby.pojo.SysDiscuss;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ren
 * @since 2023-03-28
 */
public interface SysDiscussMapper extends BaseMapper<SysDiscuss> {


    /**
     * 根据动态id查询亲友间的评论
     * @param trendId
     * @return
     */
    List<SysDiscuss> listDiscuss(Integer trendId);


    /**
     * 增加亲友间的评论
     * @param dis
     * @return
     */
    int insert(SysDiscuss dis);
}
