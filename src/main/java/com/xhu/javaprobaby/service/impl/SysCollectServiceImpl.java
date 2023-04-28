package com.xhu.javaprobaby.service.impl;

import com.xhu.javaprobaby.pojo.SysCollect;
import com.xhu.javaprobaby.mapper.SysCollectMapper;
import com.xhu.javaprobaby.service.SysCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Service
public class SysCollectServiceImpl extends ServiceImpl<SysCollectMapper, SysCollect> implements SysCollectService {

    @Autowired
    SysCollectMapper collectMapper;

    /**
     * 动态的收藏情况
     * @param trendId
     * @return
     */
    @Override
    public List<SysCollect> listCollect(Integer trendId) {
        if(trendId != null){
            return collectMapper.listCollect(trendId);
        }
        return null;
    }

    /**
     * 删除收藏
     * @param userId
     * @param trendId
     * @return
     */
    @Override
    public int removeCollect(Integer userId, Integer trendId) {
        if(userId != null && trendId != null){
            return collectMapper.removeCollect(userId,trendId);
        }
        return 0;
    }

    /**
     * 获取自己的收藏情况
     * @param userId
     * @return
     */
    @Override
    public List<SysCollect> listByUserId(Integer userId) {
        if(userId != null){
            return collectMapper.listByUserId(userId);
        }
        return null;
    }
}
