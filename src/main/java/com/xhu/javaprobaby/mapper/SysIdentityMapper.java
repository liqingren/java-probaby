package com.xhu.javaprobaby.mapper;

import com.xhu.javaprobaby.pojo.SysIdentity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xhu.javaprobaby.pojo.vo.RealtiveVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ren
 * @since 2023-03-10
 */
public interface SysIdentityMapper extends BaseMapper<SysIdentity> {

    /**
     * 根据宝宝id查询身份集合
     * @param babyId
     * @return
     */
    List<SysIdentity> listIdentity(Integer babyId);


    /**
     * 根据宝宝id查询出所有亲友
     * @param babyId
     * @return
     */
    List<RealtiveVO> list(Integer babyId);


    /**
     * 根据宝宝id和用户id删除亲友关系
     * @param babyId
     * @param userId
     * @return
     */
    int removeIdentity(@Param("babyId") Integer babyId,@Param("userId") Integer userId);

}
