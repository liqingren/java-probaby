package com.xhu.javaprobaby.service.impl;

import com.xhu.javaprobaby.pojo.SysRecord;
import com.xhu.javaprobaby.mapper.SysRecordMapper;
import com.xhu.javaprobaby.service.SysRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 成长记录表 服务实现类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Service
public class SysRecordServiceImpl extends ServiceImpl<SysRecordMapper, SysRecord> implements SysRecordService {

    @Autowired
    SysRecordMapper recordMapper;

    /**
     * 根据宝宝id获取用户所有宝宝
     * @author renliqing
     * @since 2023-03-14
     * @param babyId
     * @return
     */
    @Override
    public List<SysRecord> listRecord(Integer babyId) {
        if(babyId != null){
            return recordMapper.listRecord(babyId);
        }
        return null;
    }

    @Override
    public int insert(SysRecord record) {
        if(record != null){
            return recordMapper.insert(record);
        }
        return 0;
    }
}
