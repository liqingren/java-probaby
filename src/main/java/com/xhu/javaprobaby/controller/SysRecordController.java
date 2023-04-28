package com.xhu.javaprobaby.controller;


import com.xhu.javaprobaby.common.Result;
import com.xhu.javaprobaby.pojo.SysBaby;
import com.xhu.javaprobaby.pojo.SysRecord;
import com.xhu.javaprobaby.service.impl.SysBabyServiceImpl;
import com.xhu.javaprobaby.service.impl.SysRecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 成长记录表 前端控制器
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/javaprobaby/record")
public class SysRecordController {

    @Autowired
    SysRecordServiceImpl recordService;

    @Autowired
    SysBabyServiceImpl babyService;

    /**
     * 获取宝宝的所有生长记录
     * @param babyId
     * @return
     */
    @RequestMapping("/listrecord")
    public Result listRecord(@RequestParam("babyId") Integer babyId){
        List<SysRecord> recordList = recordService.listRecord(babyId);
        return Result.success(recordList);
    }

    /**
     * 添加成长记录
     * @param record
     * @return
     */
    @RequestMapping("/save")
    @Transactional
    public Result save(@RequestBody SysRecord record){
        try {
            int count = recordService.insert(record);
            List<SysRecord> list = recordService.listRecord(record.getBabyId());
            Boolean flag = true;
            //判断新增的记录是否为最新记录（记录时间大于以前的所有记录的记录时间）
            for(SysRecord re:list){
                if(record.getRecordTime().getTime()<re.getRecordTime().getTime()){
                    flag = false;
                }
            }
            if (count > 0) {
                if(flag) {
                    if (record.getHeight() != null || record.getWeight() != null) {
                        SysBaby baby = SysBaby.builder()
                                .babyId(record.getBabyId())
                                .height(record.getHeight())
                                .weight(record.getWeight())
                                .modifiedTime(new Date())
                                .build();
                        babyService.updateBaby(baby);
                    }
                }
                return Result.success("添加记录成功", record);
            }

        }catch (Exception e){
            e.printStackTrace();
            //保证异常捕获之后事务还会回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("系统内部错误");
        }
        return Result.fail("添加记录失败");
    }


    /**
     * 删除记录
     * @param recordId
     * @return
     */
    @RequestMapping("/remove")
    @Transactional
    public Result remove(Integer recordId){
        try {
            //获取删除的记录信息
            SysRecord record = recordService.getById(recordId);
            //获取宝宝的所有记录
            List<SysRecord> list = recordService.listRecord(record.getBabyId());
            if(list.size()>1) {
                //判断删除的那条记录是否是最新的记录
                boolean least = list.get(0).equals(record);
                if (least) {
                    //如果是最新的，则需要更新宝宝信息中的身高体重
                    SysBaby baby = babyService.getById(record.getBabyId());
                    baby.setHeight(list.get(1).getHeight());
                    baby.setWeight(list.get(1).getWeight());
                    baby.setModifiedTime(new Date());
                    babyService.updateBaby(baby);
                }

            }else{
                //如果是最新的，则需要更新宝宝信息中的身高体重
                SysBaby baby = babyService.getById(record.getBabyId());
                baby.setHeight(null);
                baby.setWeight(null);
                baby.setModifiedTime(new Date());
                babyService.updateBaby(baby);
            }
            recordService.removeById(recordId);
            return Result.success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            //保证异常捕获之后事务还会回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("系统内部错误");
        }
    }

}

