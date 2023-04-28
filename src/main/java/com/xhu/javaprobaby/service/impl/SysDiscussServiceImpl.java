package com.xhu.javaprobaby.service.impl;

import com.vdurmont.emoji.EmojiParser;
import com.xhu.javaprobaby.pojo.SysDiscuss;
import com.xhu.javaprobaby.mapper.SysDiscussMapper;
import com.xhu.javaprobaby.service.SysDiscussService;
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
 * @since 2023-03-28
 */
@Service
public class SysDiscussServiceImpl extends ServiceImpl<SysDiscussMapper, SysDiscuss> implements SysDiscussService {

    @Autowired
    SysDiscussMapper discussMapper;


    /**
     * 根据动态id查询亲友间的评论
     * @param trendId
     * @return
     */
    @Override
    public List<SysDiscuss> listDiscuss(Integer trendId) {
        if(trendId != null){
            List<SysDiscuss> list = discussMapper.listDiscuss(trendId);
            for(SysDiscuss dis:list){
                if(dis.getContent() != null && !dis.getContent().equals("")){
                    dis.setContent(EmojiParser.parseToUnicode(dis.getContent()));
                }
            }
            return list;
        }
        return null;
    }

    @Override
    public int insert(SysDiscuss dis) {
        if(dis != null){
            if(dis.getContent() != null && !dis.getContent().equals("")){
                dis.setContent(EmojiParser.parseToAliases(dis.getContent()));
            }
            return discussMapper.insert(dis);
        }
        return 0;
    }


}
