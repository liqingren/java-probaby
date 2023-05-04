package com.xhu.javaprobaby.mapper;

import com.xhu.javaprobaby.pojo.SysRegion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 区域表 Mapper 接口
 * </p>
 *
 * @author ren
 * @since 2023-05-04
 */
public interface SysRegionMapper extends BaseMapper<SysRegion> {

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
