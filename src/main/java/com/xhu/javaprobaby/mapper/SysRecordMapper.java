package com.xhu.javaprobaby.mapper;

import com.xhu.javaprobaby.pojo.SysRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 成长记录表 Mapper 接口
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
public interface SysRecordMapper extends BaseMapper<SysRecord> {

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
