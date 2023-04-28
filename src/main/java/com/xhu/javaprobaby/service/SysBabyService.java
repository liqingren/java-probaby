package com.xhu.javaprobaby.service;

import com.xhu.javaprobaby.pojo.SysBaby;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 宝宝信息表 服务类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
public interface SysBabyService extends IService<SysBaby> {

    /**
     * 根据用户id获取用户的所有宝宝
     * @param userId 用户id
     * @return
     */
    List<SysBaby> listBabyByUserId(Integer userId);

    /**
     * 修改宝宝信息
     * @param baby
     * @return 受影响行数
     */
    int updateBaby(SysBaby baby);

    /**
     * 增加宝宝
     * @param baby
     * @return
     */
    int insert(SysBaby baby);

}
