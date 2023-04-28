package com.xhu.javaprobaby.service.impl;

import com.xhu.javaprobaby.pojo.SysFollow;
import com.xhu.javaprobaby.mapper.SysFollowMapper;
import com.xhu.javaprobaby.service.SysFollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 关注表 服务实现类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Service
public class SysFollowServiceImpl extends ServiceImpl<SysFollowMapper, SysFollow> implements SysFollowService {

    @Autowired
    SysFollowMapper followMapper;

    @Override
    public List<SysFollow> listFollow(Integer userId) {
        if(userId != null){
            return followMapper.listFollow(userId);
        }
        return null;
    }

    @Override
    public int remove(Integer followerId, Integer concernedId) {
        if(followerId != null && concernedId != null){
            return followMapper.remove(followerId, concernedId);
        }
        return 0;
    }
}
