package com.xhu.javaprobaby.service;

import com.xhu.javaprobaby.pojo.SysComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhu.javaprobaby.pojo.vo.CommentVO;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
public interface SysCommentService extends IService<SysComment> {
    /**
     * 评论
     * @param trendId
     * @return
     */
    List<CommentVO> listComment(Integer trendId);

    /**
     * 评论
     * @param trendId
     * @return
     */
    List<SysComment> listComment2(Integer trendId);

    /**
     * 增加评论
     * @param comment
     * @return
     */
    int insertComment(SysComment comment);


    /**
     * 根据用户id修改评论表中的用户信息（用户名或用户头像）
     * @param comment
     * @return
     */
    int update(SysComment comment);


    /**
     * 根据userId查询评论
     * @param userId
     * @return
     */
    List<SysComment> listByUserId(Integer userId);

    /**
     * 根据父id查询评论
     * @param parentId
     * @return
     */
    List<SysComment> listByParentId(Integer parentId);

    /**
     * 根据parentId删除评论
     * @param parentId
     * @return
     */
    int remove(Integer parentId);

}
