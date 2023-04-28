package com.xhu.javaprobaby.controller;


import com.xhu.javaprobaby.common.Result;
import com.xhu.javaprobaby.pojo.SysTag;
import com.xhu.javaprobaby.service.impl.SysTagServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>
 * 大事记(标签)表 前端控制器
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/javaprobaby/tag")
public class SysTagController {

    private static ConcurrentMap<String,SysTag> map = new ConcurrentHashMap<>();


    @Autowired
    SysTagServiceImpl tagService;

    /**
     * 增加标签
     * @param content
     * @return
     */
    @RequestMapping("/save")
    public Result save(@RequestParam String content){
        SysTag tag = map.get(content);
        //判断是否已存在该标签，若不存在，则存入数据库，并获取到标签（id和内容），若存在，则直接返回标签给前端
        if(tag == null){
            tag = SysTag.builder()
                    .content(content)
                    .build();
            boolean flag = tagService.save(tag);
            if(flag){
                tag = tagService.getTag(content);
                map.put(content,tag);
            }
        }

        return Result.success(tag);
    }

    /**
     * 程序一启动就获取所有的tag标签
     */
    @PostConstruct
    public void start(){
        List<SysTag> tags = tagService.listTag();
        for(SysTag tag:tags){
            map.put(tag.getContent(),tag);
        }
    }


    /**
     * 接口：获取所有标签
     * @return
     */
    @RequestMapping("/listtags")
    public Result listTag(){
        Set<SysTag> tags =  new HashSet<>();
        for(SysTag tag:map.values()){
            SysTag tagVo = new SysTag();
            BeanUtils.copyProperties(tag,tagVo); //通过copyProperties()可以放置直接对对象赋值改变tag的值
            String content = tagVo.getContent();
            if(content.indexOf("第一次") != -1){
                content = content.substring(content.indexOf("第一次")+3);
                tagVo.setContent(content);
            }
            tagVo.setTagId(null);
            tags.add(tagVo);
        }

        return Result.success(tags);
    }

}

