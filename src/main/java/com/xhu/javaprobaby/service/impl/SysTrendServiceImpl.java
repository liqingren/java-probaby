package com.xhu.javaprobaby.service.impl;

import com.vdurmont.emoji.EmojiParser;
import com.xhu.javaprobaby.pojo.SysTrend;
import com.xhu.javaprobaby.mapper.SysTrendMapper;
import com.xhu.javaprobaby.pojo.vo.TrendVO;
import com.xhu.javaprobaby.service.SysTrendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 动态表 服务实现类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Service
public class SysTrendServiceImpl extends ServiceImpl<SysTrendMapper, SysTrend> implements SysTrendService {

    @Autowired
    SysTrendMapper trendMapper;

    /**
     * 根据宝宝id获取宝宝的记录动态
     * @return
     */
    @Override
    public List<TrendVO> listTrend(Integer babyId, Integer userId) {
        if(babyId != null){
            return trendMapper.listTrend(babyId,userId);
        }
        return null;
    }

    /**
     * 根据动态id获取首页动态详情
     * @param trendId
     * @return
     */
    @Override
    public TrendVO gettrendByIndex(Integer trendId) {
        if(trendId != null){
            return trendMapper.gettrendByIndex(trendId);
        }
        return null;
    }


    /**
     * 根据用户id查询动态
     * @param userId
     * @return
     */
    @Override
    public List<TrendVO> listTrends(Integer userId) {
        if(userId != null){
            return trendMapper.listTrends(userId);
        }
        return null;
    }

    /**
     * 获取动态详情
     * @param trendId
     * @return
     */
    @Override
    public TrendVO getTrend(Integer trendId) {
        if(trendId != null){
            return trendMapper.getTrend(trendId);
        }
        return null;
    }

    /**
     * 新增动态：将内容转码（防止有emoji表情包）
     * @param trend
     * @return
     */
    @Override
    public int insert(SysTrend trend) {
        if(trend != null){
            if(trend.getContent() != null && !trend.getContent().equals("")) {
                trend.setContent(EmojiParser.parseToAliases(trend.getContent()));
            }
            return trendMapper.insert(trend);
        }
        return 0;
    }


    /**
     * 获取全部动态
     * @return
     */
    @Override
    public List<TrendVO> listAll() {
        return trendMapper.listAll();
    }

    /**
     * 根据宝宝id查询动态（动态id）
     * @param babyId
     * @return
     */
    @Override
    public List<Integer> listByBabyId(Integer babyId) {
        if(babyId != null){
            return trendMapper.listByBabyId(babyId);
        }
        return null;
    }

    /**
     * 根据userId修改动态表中的用户信息
     * @param trend
     * @return
     */
    @Override
    public int update(SysTrend trend) {
        if(trend != null){
            if(trend.getContent() != null && !trend.getContent().equals("")){
                trend.setContent(EmojiParser.parseToAliases(trend.getContent()));
            }
            return trendMapper.update(trend);
        }
        return 0;
    }

    /**
     * 修改动态
     * @param trend
     * @return
     */
    @Override
    public int updateTrend(SysTrend trend) {
        if(trend != null){
            if(trend.getContent() != null && !trend.getContent().equals("")){
                trend.setContent(EmojiParser.parseToAliases(trend.getContent()));
            }
            trendMapper.updateTrend(trend);
        }
        return 0;
    }




}
