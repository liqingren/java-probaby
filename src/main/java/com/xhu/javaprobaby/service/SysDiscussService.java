package com.xhu.javaprobaby.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xhu.javaprobaby.pojo.SysDiscuss;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ren
 * @since 2023-03-28
 */
public interface SysDiscussService extends IService<SysDiscuss> {

    /**
     * 根据动态id查询亲友间的评论
     * @param trendId
     * @return
     */
    List<SysDiscuss> listDiscuss(Integer trendId);

//    /**
//     * 根据动态id查询亲友间的评论
//     * @param trendId
//     * @return
//     */
//    List<DiscussVO> listDiscuss(Integer trendId);

    /**
     * 增加亲友间的评论
     * @param dis
     * @return
     */
    int insert(SysDiscuss dis);
}
