package com.xhu.javaprobaby.mapper;

import com.xhu.javaprobaby.pojo.SysLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 点赞表 Mapper 接口
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
public interface SysLikeMapper extends BaseMapper<SysLike> {

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
    int removeLike(@Param("userId") Integer userId,@Param("trendId") Integer trendId);


    /**
     * 获取用户的点赞
     * @param userId
     * @return
     */
    List<SysLike> listByUserId(Integer userId);
}
