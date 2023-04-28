package com.xhu.javaprobaby.service;

import com.xhu.javaprobaby.pojo.SysTrend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhu.javaprobaby.pojo.vo.TrendVO;

import java.util.List;

/**
 * <p>
 * 动态表 服务类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
public interface SysTrendService extends IService<SysTrend> {

    /**
     * 根据宝宝id获取宝宝的记录动态
     * @return
     */
    List<TrendVO> listTrend(Integer babyId, Integer userId);

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
     * 获取动态详情
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
     * 根据宝宝id查询动态
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
