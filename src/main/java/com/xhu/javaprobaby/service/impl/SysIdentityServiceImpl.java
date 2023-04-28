package com.xhu.javaprobaby.service.impl;

import com.xhu.javaprobaby.pojo.SysIdentity;
import com.xhu.javaprobaby.mapper.SysIdentityMapper;
import com.xhu.javaprobaby.pojo.vo.RealtiveVO;
import com.xhu.javaprobaby.service.SysIdentityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ren
 * @since 2023-03-10
 */
@Service
public class SysIdentityServiceImpl extends ServiceImpl<SysIdentityMapper, SysIdentity> implements SysIdentityService {

    @Autowired
    SysIdentityMapper identityMapper;

    @Override
    public List<SysIdentity> listIdentity(Integer babyId) {
        if(babyId != null){
            return identityMapper.listIdentity(babyId);
        }
        return null;
    }

    @Override
    public List<RealtiveVO> list(Integer babyId) {
        if(babyId != null){
            return identityMapper.list(babyId);
        }
        return null;
    }

    @Override
    public int removeIdentity(Integer babyId, Integer userId) {
        if(babyId != null && userId != null){
            return identityMapper.removeIdentity( babyId,userId);
        }
        return 0;
    }
}
