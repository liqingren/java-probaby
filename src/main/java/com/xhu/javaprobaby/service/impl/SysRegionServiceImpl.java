package com.xhu.javaprobaby.service.impl;

import com.xhu.javaprobaby.pojo.SysRegion;
import com.xhu.javaprobaby.mapper.SysRegionMapper;
import com.xhu.javaprobaby.service.SysRegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 区域表 服务实现类
 * </p>
 *
 * @author ren
 * @since 2023-05-04
 */
@Service
public class SysRegionServiceImpl extends ServiceImpl<SysRegionMapper, SysRegion> implements SysRegionService {

    @Autowired
    SysRegionMapper regionMapper;

    /**
     * 获取省市地区
     * @return
     */
    @Override
    public List<SysRegion> listRegion() {
        List<SysRegion> regionList = new ArrayList<>();
        List<SysRegion> list = regionMapper.listRegion();
        for(SysRegion re:list){
            if(re.getPid()==0){
                SysRegion region = new SysRegion();
                region.setId(re.getId());
                region.setPid(re.getPid());
                region.setCode(re.getCode());
                region.setName(re.getName());
                region.setFullname(re.getFullname());
                region.setFirstLetter(re.getFirstLetter());
                region.setFullLetter(re.getFullLetter());
                region.setLat(re.getLat());
                region.setLng(re.getLng());
                region.setLevel(re.getLevel());
                regionList.add(region);
            }
        }
        for(SysRegion reg:regionList){
            regionHelper(reg,list);
        }
        return regionList;
    }

    /**
     * 获取所有的省
     * @return
     */
    @Override
    public List<SysRegion> listProvince() {
        return regionMapper.listProvince();
    }


    /**
     * 递归出市
     * @param region
     * @param list
     */
    public void regionHelper(SysRegion region,List<SysRegion> list){
        if(region == null){
            return;
        }
        for(SysRegion re:list){
            if(re.getPid() == region.getId()){
                SysRegion child = new SysRegion();
                child.setId(re.getId());
                child.setPid(re.getPid());
                child.setCode(re.getCode());
                child.setName(re.getName());
                child.setFullname(re.getFullname());
                child.setFirstLetter(re.getFirstLetter());
                child.setFullLetter(re.getFullLetter());
                child.setLat(re.getLat());
                child.setLng(re.getLng());
                child.setLevel(re.getLevel());
                region.getChildren().add(child);
            }
        }
        for(SysRegion reg:region.getChildren()){
            regionHelper(reg,list);
        }
    }
}
