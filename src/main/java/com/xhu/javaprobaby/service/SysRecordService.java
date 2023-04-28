package com.xhu.javaprobaby.service;

import com.xhu.javaprobaby.pojo.SysRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 成长记录表 服务类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
public interface SysRecordService extends IService<SysRecord> {

    /**
     * 获取宝宝的所有记录
     * @param babyId
     * @return
     */
    List<SysRecord> listRecord(Integer babyId);


    /**
     * 增加记录
     * @param record
     * @return
     */
    int insert(SysRecord record);
}
