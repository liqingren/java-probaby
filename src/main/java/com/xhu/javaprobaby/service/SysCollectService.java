package com.xhu.javaprobaby.service;

import com.xhu.javaprobaby.pojo.SysCollect;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 收藏表 服务类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
public interface SysCollectService extends IService<SysCollect> {
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
    int removeCollect(Integer userId,Integer trendId);


    /**
     * 获取自己的收藏数量
     * @param userId
     * @return
     */
    List<SysCollect> listByUserId(Integer userId);
}
