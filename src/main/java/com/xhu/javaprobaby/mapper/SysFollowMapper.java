package com.xhu.javaprobaby.mapper;

import com.xhu.javaprobaby.pojo.SysFollow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 关注表 Mapper 接口
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
public interface SysFollowMapper extends BaseMapper<SysFollow> {

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
    int remove(@Param("followerId") Integer followerId,@Param("concernedId") Integer concernedId);



}
