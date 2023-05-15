package com.xhu.javaprobaby.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xhu.javaprobaby.common.Result;
import com.xhu.javaprobaby.pojo.SysBaby;
import com.xhu.javaprobaby.pojo.SysIdentity;
import com.xhu.javaprobaby.pojo.vo.BabyVO;
import com.xhu.javaprobaby.pojo.vo.RealtiveVO;
import com.xhu.javaprobaby.service.impl.SysIdentityServiceImpl;
import com.xhu.javaprobaby.util.QRcodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ren
 * @since 2023-03-10
 */
@RestController
@RequestMapping("/javaprobaby/identity")
public class SysIdentityController {


    @Autowired
    SysIdentityServiceImpl identityService;

    /**
     * 获取所有亲友
     * @param babyId
     * @return
     */
    @RequestMapping("/list")
    public Result list(@RequestParam("babyId") Integer babyId){
        List<RealtiveVO> list = identityService.list(babyId);
        return Result.success(list);

    }

    /**
     * 添加亲友
     * @param identity
     * @return
     */
    @RequestMapping("/save")
    public Result save(@RequestBody SysIdentity identity){
        boolean flag = identityService.save(identity);
        if(flag){
            return Result.success("成功");
        }
        return Result.fail("失败");
    }


    /**
     * 邀请亲友，生成宝宝信息的二维码
     * @param data
     * @return
     */
    @RequestMapping("/invite")
    public Result invite(@RequestBody Map<String,Object> data){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            BabyVO vo = JSON.parseObject(JSON.toJSONString(data.get("baby")), BabyVO.class);
            SysBaby baby = SysBaby.builder()
                    .babyId(vo.getBabyId())
                    .userId(vo.getUserId())
                    .nickname(vo.getNickname())
                    .babySex(vo.getBabySex())
                    .babyPhoto(vo.getBabyPhoto())
                    .babyBlood(vo.getBabyBlood())
                    .babyBirth(vo.getBabyBirth())
                    .height(vo.getHeight())
                    .weight(vo.getWeight())
                    .build();
            //判断时刻是否为空
            if(vo.getBabyMoment() != null && !vo.getBabyMoment().equals("")){
                baby.setBabyMoment(sdf.parse(vo.getBabyMoment()));
            }
            String pagePath = (String) data.get("path");

            String strBaby = JSONObject.toJSONString(baby, SerializerFeature.PrettyFormat,
                    SerializerFeature.WriteMapNullValue);//不忽略对象中属性值为空的字段
            String content = strBaby + "\\" + pagePath;//拼接二维码内容
            //生成邀请二维码
            String base64 = QRcodeUtils.getCodeImage(content);
            return Result.success("成功",base64);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("系统内部错误");
        }
    }




}

