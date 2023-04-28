package com.xhu.javaprobaby.service;

import com.xhu.javaprobaby.pojo.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据手机号或openid查询用户
     * @author renliqing
     * @since 2023-03-01
     * @param value 手机号或openid
     * @return 对象
     */
    SysUser getUser(String value);

    /**
     * 修改个人信息
     * @author renliqing
     * @since 2023-03-02
     * @param user 对象
     * @return 受影响行数
     */
    int update(SysUser user);

    /**
     * 获取所有用户id
     * @return
     */
    List<Integer> listUserId();


    /**
     * 根据手机号修改密码
     * @param phone
     * @param password
     * @return
     */
    int updatePass(String password, String phone);

}
