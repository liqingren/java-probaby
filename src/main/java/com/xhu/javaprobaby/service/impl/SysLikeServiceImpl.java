package com.xhu.javaprobaby.service.impl;

import com.xhu.javaprobaby.pojo.SysLike;
import com.xhu.javaprobaby.mapper.SysLikeMapper;
import com.xhu.javaprobaby.service.SysLikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 点赞表 服务实现类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Service
public class SysLikeServiceImpl extends ServiceImpl<SysLikeMapper, SysLike> implements SysLikeService {

    @Autowired
    SysLikeMapper likeMapper;

    @Override
    public List<SysLike> listLike(Integer trendId) {
        if(trendId != null ){
            return likeMapper.listLike(trendId);
        }
        return null;
    }

    @Override
    public int removeLike(Integer userId, Integer trendId) {
        if(userId != null && trendId != null){
            return likeMapper.removeLike(userId, trendId);
        }
        return 0;
    }

    @Override
    public List<SysLike> listByUserId(Integer userId) {
        if(userId != null){
            return likeMapper.listByUserId(userId);
        }
        return null;
    }
}
