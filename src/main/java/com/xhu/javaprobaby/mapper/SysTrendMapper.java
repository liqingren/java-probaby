package com.xhu.javaprobaby.mapper;

import com.xhu.javaprobaby.pojo.SysTrend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xhu.javaprobaby.pojo.vo.TrendVO;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * <p>
 * 动态表 Mapper 接口
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
public interface SysTrendMapper extends BaseMapper<SysTrend> {

    /**
     * 根据宝宝id获取宝宝的记录动态
     * @return
     */
    List<TrendVO> listTrend(@Param("babyId") Integer babyId,@Param("userId") Integer userId);


    /**
     * 根据动态id获取首页动态详情
     * @param trendId
     * @return
     */
    TrendVO gettrendByIndex(Integer trendId);



    /**
     * 获取自己有权限查看的所有动态
     * @param userId
     * @return
     */
    List<TrendVO> listTrends(Integer userId);


    /**
     * 获取社区动态详情
     * @param trendId
     * @return
     */
    TrendVO getTrend(Integer trendId);

    /**
     * 增加动态
     * @param trend
     * @return
     */
    int insert(SysTrend trend);

    /**
     * 获取全部动态
     * @return
     */
    List<TrendVO> listAll();


    /**
     * 根据宝宝id查询动态（动态id）
     * @param babyId
     * @return
     */
    List<Integer> listByBabyId(Integer babyId);




    /**
     * 根据userId修改动态表中的用户信息
     * @param trend
     * @return
     */
    int update(SysTrend trend);


    /**
     * 修改动态
     * @param trend
     * @return
     */
    int updateTrend(SysTrend trend);

}
