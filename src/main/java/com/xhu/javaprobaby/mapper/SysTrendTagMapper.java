package com.xhu.javaprobaby.mapper;

import com.xhu.javaprobaby.pojo.SysTrendTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 动态-标签表 Mapper 接口
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
public interface SysTrendTagMapper extends BaseMapper<SysTrendTag> {

    /**
     * 查询用户的标签关系
     * @param trendId
     * @return
     */
    List<SysTrendTag> listTrendTag(Integer trendId);


    /**
     * 根据动态id删除动态-标签表数据
     * @param trendId
     * @return
     */
    int remove(Integer trendId);


    /**
     * 获取第一次的标签
     * @return
     */
    List<SysTrendTag> listFirst();
}
