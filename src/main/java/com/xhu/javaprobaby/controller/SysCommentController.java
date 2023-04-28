package com.xhu.javaprobaby.controller;


import com.vdurmont.emoji.EmojiParser;
import com.xhu.javaprobaby.common.Result;
import com.xhu.javaprobaby.pojo.SysComment;
import com.xhu.javaprobaby.pojo.vo.CommentVO;
import com.xhu.javaprobaby.service.impl.SysCommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/javaprobaby/comment")
public class SysCommentController {

    @Autowired
    SysCommentServiceImpl commentService;

    /**
     * 评论
     * @param comment
     * @return
     */
    @RequestMapping("/save")
    public Result save(@RequestBody SysComment comment){
        comment.setCommentTime(new Date());
        //将评论内容通过EmojiParser转码一下变成string字符串（放置有emoji表情包评论）
        comment.setContent(EmojiParser.parseToAliases(comment.getContent()));
        int count = commentService.insertComment(comment);
        if(count>0){
            return Result.success(comment);
        }
        return Result.fail("评论失败");
    }


    /**
     * 删除评论：如果有parentId == commentId，则一起删除
     * @param commentId
     * @return
     */
    @RequestMapping("/remove")
    @Transactional
    public Result remove(@RequestParam("commentId") Integer commentId){
        boolean flag = commentService.removeById(commentId);
        if(flag){
            List<SysComment> list = commentService.listByParentId(commentId);
            if(list.size()>0){
                commentService.remove(commentId);
            }
            return Result.success("评论删除成功");
        }
        return Result.fail("评论删除失败");
    }


    @RequestMapping("/listcomment")
    public Result listComment(@RequestParam("trendId") Integer trendId){
        List<CommentVO> list = commentService.listComment(trendId);
        return Result.success(list);
    }


}

