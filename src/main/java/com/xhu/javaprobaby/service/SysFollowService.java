package com.xhu.javaprobaby.service;

import com.xhu.javaprobaby.pojo.SysFollow;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 关注表 服务类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
public interface SysFollowService extends IService<SysFollow> {

    /**
     * 查询用户关注的人
     * @param userId
     * @return
     */
    List<SysFollow> listFollow(Integer userId);


    /**
     * 取消关注
     * @param followerId
     * @param concernedId
     * @return
     */
    int remove(@Param("followerId") Integer followerId, @Param("concernedId") Integer concernedId);
}
