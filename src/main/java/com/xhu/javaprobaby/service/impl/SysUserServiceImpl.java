package com.xhu.javaprobaby.service.impl;

import com.xhu.javaprobaby.pojo.SysUser;
import com.xhu.javaprobaby.mapper.SysUserMapper;
import com.xhu.javaprobaby.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysUserMapper userMapper;

    /**
     * 根据手机号或openid查询用户
     * @author renliqing
     * @since 2023-03-01
     * @param value 手机号或openid
     * @return 对象
     */
    @Override
    public SysUser getUser(String value) {
        if(value == null){
            return null;
        }
        return userMapper.getUser(value);
    }

    /**
     * 修改个人信息
     * @author renliqing
     * @since 2023-03-02
     * @param user 对象
     * @return 受影响行数
     */
    @Override
    public int update(SysUser user) {
        if(user == null){
            return 0;
        }
        return userMapper.update(user);
    }

    /**
     * 获取所有用户id
     * @author renliqing
     * @since 2023-03-16
     * @return
     */
    @Override
    public List<Integer> listUserId() {
        return userMapper.listUserId();
    }

    /**
     * 根据手机号修改密码
     * @param phone
     * @param password
     * @return
     */
    @Override
    public int updatePass(String password, String phone) {
        return userMapper.updatePass(password,phone,new Date());
    }
}
