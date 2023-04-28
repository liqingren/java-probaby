package com.xhu.javaprobaby.service.impl;

import com.xhu.javaprobaby.pojo.SysTrendTag;
import com.xhu.javaprobaby.mapper.SysTrendTagMapper;
import com.xhu.javaprobaby.service.SysTrendTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 动态-标签表 服务实现类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Service
public class SysTrendTagServiceImpl extends ServiceImpl<SysTrendTagMapper, SysTrendTag> implements SysTrendTagService {

    @Autowired
    SysTrendTagMapper trendTagMapper;

    @Override
    public List<SysTrendTag> listTrendTag(Integer trendId) {
        if(trendId != null){
            return trendTagMapper.listTrendTag(trendId);
        }
        return null;
    }

    @Override
    public int remove(Integer trendId) {
        if(trendId != null){
            return trendTagMapper.remove(trendId);
        }
        return 0;
    }


    @Override
    public List<SysTrendTag> listFirst() {
        return trendTagMapper.listFirst();
    }
}
