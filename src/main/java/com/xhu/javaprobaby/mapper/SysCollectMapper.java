package com.xhu.javaprobaby.mapper;

import com.xhu.javaprobaby.pojo.SysCollect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 收藏表 Mapper 接口
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
public interface SysCollectMapper extends BaseMapper<SysCollect> {

    /**
     * 动态的收藏情况
     * @param trendId
     * @return
     */
    List<SysCollect> listCollect(Integer trendId);


    /**
     * 删除收藏
     * @param userId
     * @param trendId
     * @return
     */
    int removeCollect(@Param("userId") Integer userId,@Param("trendId") Integer trendId);


    /**
     * 获取自己的收藏情况
     * @param userId
     * @return
     */
    List<SysCollect> listByUserId(Integer userId);





}
