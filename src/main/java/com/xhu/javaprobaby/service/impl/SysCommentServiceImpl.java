package com.xhu.javaprobaby.service.impl;

import com.vdurmont.emoji.EmojiParser;
import com.xhu.javaprobaby.pojo.SysComment;
import com.xhu.javaprobaby.mapper.SysCommentMapper;
import com.xhu.javaprobaby.pojo.vo.CommentVO;
import com.xhu.javaprobaby.service.SysCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Service
public class SysCommentServiceImpl extends ServiceImpl<SysCommentMapper, SysComment> implements SysCommentService {

    @Autowired
    SysCommentMapper commentMapper;




    /**
     * 获取动态评论
     * @param trendId
     * @return
     */
    @Override
    public List<CommentVO> listComment(Integer trendId) {
        List<CommentVO> commentList = new ArrayList<>();
        if(trendId != null){
            List<SysComment> list = commentMapper.listComment(trendId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            for(SysComment com:list){
                if(com.getParentId() == 0){
                    CommentVO comment = new CommentVO();
                    comment.setCommentId(com.getCommentId());
                    comment.setUserId(com.getUserId());
                    comment.setUsername(com.getUsername());
                    comment.setUserPhoto(com.getUserPhoto());
                    comment.setTrendId(com.getTrendId());
                    if(com.getContent() != null && !com.getContent().equals("")){
                        //如果内容不为空，使用EmojiParser将congtent转码一下（以防有emoji表情包的评论内容）
                        comment.setContent(EmojiParser.parseToUnicode(com.getContent()));
                    }
                    comment.setMedia(com.getMedia());
                    comment.setCommentTime(sdf.format(com.getCommentTime()));
                    comment.setCount(list.size());
                    comment.setParentId(com.getParentId());
                    commentList.add(comment);
                }
            }
            for(CommentVO com:commentList){
                commentHelper(com,list);
            }
            return commentList;
        }
        return null;
    }

    @Override
    public List<SysComment> listComment2(Integer trendId) {
        if(trendId != null){
            return commentMapper.listComment(trendId);
        }
        return null;
    }

    /**
     * 增加评论
     * @param comment
     * @return
     */
    @Override
    public int insertComment(SysComment comment) {
        if(comment != null){
            return commentMapper.insertComment(comment);
        }
        return 0;
    }

    @Override
    public int update(SysComment comment) {
        if(comment != null){
            return commentMapper.update(comment);
        }
        return 0;
    }

    @Override
    public List<SysComment> listByUserId(Integer userId) {
        if(userId != null){
            return commentMapper.listByUserId(userId);
        }
        return null;
    }

    @Override
    public List<SysComment> listByParentId(Integer parentId) {
        if(parentId != null){
            return commentMapper.listByParentId(parentId);
        }
        return null;
    }

    @Override
    public int remove(Integer parentId) {
        if(parentId != null){
            return commentMapper.remove(parentId);
        }
        return 0;
    }

    /**
     * 递归出子级评论
     * @param comment
     * @param list
     */
    private void commentHelper(CommentVO comment,List<SysComment> list){
        if(comment == null){
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for(SysComment com:list){
            if(com.getParentId() == comment.getCommentId() || com.getParentId().equals(comment.getCommentId())){
                CommentVO child = new CommentVO();
                child.setCommentId(com.getCommentId());
                child.setUserId(com.getUserId());
                child.setUsername(com.getUsername());
                child.setUserPhoto(com.getUserPhoto());
                child.setTrendId(com.getTrendId());
                if(com.getContent() != null && !com.getContent().equals("")){
                    //如果内容不为空，使用EmojiParser将congtent转码一下（以防有emoji表情包的评论内容）
                    child.setContent(EmojiParser.parseToUnicode(com.getContent()));
                }
                child.setMedia(com.getMedia());
                child.setCommentTime(sdf.format(com.getCommentTime()));
                child.setParentId(com.getParentId());
                comment.getChildren().add((child));
            }
        }
        for(CommentVO com:comment.getChildren()){
            commentHelper(com,list);
        }
    }

}
