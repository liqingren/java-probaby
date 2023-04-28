package com.xhu.javaprobaby.service.impl;

import com.xhu.javaprobaby.pojo.SysTag;
import com.xhu.javaprobaby.mapper.SysTagMapper;
import com.xhu.javaprobaby.service.SysTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 大事记(标签)表 服务实现类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Service
public class SysTagServiceImpl extends ServiceImpl<SysTagMapper, SysTag> implements SysTagService {

    @Autowired
    SysTagMapper tagMapper;

    /**
     * 获取所有标签
     * @return
     */
    @Override
    public List<SysTag> listTag() {
        return tagMapper.listTag();
    }

    /**
     * 根据标签内容获取标签
     * @param content
     * @return
     */
    @Override
    public SysTag getTag(String content) {
        if(content != null && !content.equals("")){
            return tagMapper.getTag(content);
        }
        return null;
    }

    /**
     * 根据动态id获取标签
     * @param trendId
     * @return
     */
    @Override
    public List<SysTag> listTags(Integer trendId) {
        if(trendId != null){
            return tagMapper.listTags(trendId);
        }
        return null;
    }
}
