package com.xhu.javaprobaby.service;

import com.xhu.javaprobaby.pojo.SysRegion;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 区域表 服务类
 * </p>
 *
 * @author ren
 * @since 2023-05-04
 */
public interface SysRegionService extends IService<SysRegion> {

    /**
     * 获取所有的省市
     * @return
     */
    List<SysRegion> listRegion();

    /**
     * 获取所有的省
     * @return
     */
    List<SysRegion> listProvince();

}
