package com.xhu.javaprobaby.service;

import com.xhu.javaprobaby.pojo.SysLike;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 点赞表 服务类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
public interface SysLikeService extends IService<SysLike> {
    /**
     * 动态的点赞数
     * @param trendId
     * @return
     */
    List<SysLike> listLike(Integer trendId);

    /**
     * 取消点赞
     * @param userId
     * @param trendId
     * @return
     */
    int removeLike(Integer userId, Integer trendId);


    /**
     * 获取用户的点赞
     * @param userId
     * @return
     */
    List<SysLike> listByUserId(Integer userId);
}
