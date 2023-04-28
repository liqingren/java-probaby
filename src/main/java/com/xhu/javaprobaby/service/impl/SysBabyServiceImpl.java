package com.xhu.javaprobaby.service.impl;

import com.xhu.javaprobaby.pojo.SysBaby;
import com.xhu.javaprobaby.mapper.SysBabyMapper;
import com.xhu.javaprobaby.service.SysBabyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 宝宝信息表 服务实现类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Service
public class SysBabyServiceImpl extends ServiceImpl<SysBabyMapper, SysBaby> implements SysBabyService {

    @Autowired
    SysBabyMapper babyMapper;


    /**
     * 根据用户id获取用户的所有宝宝
     * @author renliqing
     * @since 2023-03-13
     * @param userId 用户id
     * @return
     */
    @Override
    public List<SysBaby> listBabyByUserId(Integer userId) {
        if(userId != null){
            return babyMapper.listBabyByUserId(userId);
        }
        return null;
    }

    /**
     * 修改宝宝信息
     * @author renliqing
     * @since 2023-03-14
     * @param baby
     * @return 受影响行数
     */
    @Override
    public int updateBaby(SysBaby baby) {
        if(baby != null){
            return babyMapper.updateBaby(baby);
        }
        return 0;
    }

    /**
     * 增加宝宝
     * @param baby
     * @return
     */
    @Override
    public int insert(SysBaby baby) {
        if(baby != null){
            return babyMapper.insert(baby);
        }
        return 0;
    }

}
