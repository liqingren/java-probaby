package com.xhu.javaprobaby.mapper;

import com.xhu.javaprobaby.pojo.SysComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xhu.javaprobaby.pojo.vo.CommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
public interface SysCommentMapper extends BaseMapper<SysComment> {

    /**
     * 评论
     * @param trendId
     * @return
     */
    List<SysComment> listComment(Integer trendId);


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
