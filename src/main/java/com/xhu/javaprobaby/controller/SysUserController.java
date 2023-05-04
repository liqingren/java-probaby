package com.xhu.javaprobaby.controller;

import com.alibaba.fastjson.JSONObject;
import com.xhu.javaprobaby.common.Result;
import com.xhu.javaprobaby.pojo.*;
import com.xhu.javaprobaby.pojo.vo.TrendVO;
import com.xhu.javaprobaby.service.impl.*;
import com.xhu.javaprobaby.util.DESUtils;
import com.xhu.javaprobaby.util.JwtUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/javaprobaby/user")
public class SysUserController {

    @Autowired
    SysUserServiceImpl userService;

    @Autowired
    SysFollowServiceImpl followService;


    @Autowired
    SysTrendServiceImpl trendService;

    @Autowired
    SysCommentServiceImpl commentService;

    @Autowired
    SysRegionServiceImpl regionService;

    @Value("${mini.appid}")
    private String appid;

    @Value("${mini.secret}")
    private String secret;

    /**
     *  登录：根据code、appid、secret(密钥)获取到用户唯一标识openid，会话密钥session_key，然后根据openid判断用户
     *  是否是第一次登录，若第一次登录，则将微信用户昵称和头像以及openid存入用户表，若不是，则直接返回数据
     * @author renliqing
     * @since 2023-03-01
     * @param username 微信昵称
     * @param userPhoto 微信头像
     * @param code 临时登录凭证code
     * @return result
     * @throws IOException
     */
    @RequestMapping("/minilogin")
    public Result login(@RequestParam("username") String username,
                        @RequestParam("userPhoto") String userPhoto,
                        @RequestParam("code") String code) {
        try {
            if (code == null) {
                return null;
            }
            //粗行间一个client请求
            HttpClient client = HttpClientBuilder.create().build();
            //构建get请求
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid
                    + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
            HttpGet get = new HttpGet(url);
            //发送请求
            HttpResponse response = null;
            String result = null;
            try {
                response = client.execute(get);
                result = EntityUtils.toString(response.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = JSONObject.parseObject(result);
            //获取openid
            String openid = jsonObject.getString("openid");

            //创建map，将jsonObject和user用户信息放入其中
            Map<String, Object> map = new HashMap<>();
            map.put("wechatInfo", jsonObject);

            //根据openid获取用户信息，判断用户是否是第一次登录
            SysUser userVO = userService.getUser(openid);
            Boolean flag = false;
            if (userVO == null) {
                //第一次登录，则将用户昵称，头像和openid存入用户表
                SysUser saveUser = SysUser.builder()
                        .username(username)
                        .userPhoto(userPhoto)
                        .openId(openid)
                        .build();
                flag = userService.save(saveUser);
                //获取到用户信息
                saveUser = userService.getUser(openid);
                if (flag) {
                    map.put("user", saveUser);
                    //获取token
                    String token = JwtUtils.setToken(saveUser);
                    map.put("token", token);
                    return Result.success("登录成功", map);
                }
            } else {
                //不是第一次登录，直接将user对象存入map中
                map.put("user", userVO);
                //获取token
                String token = JwtUtils.setToken(userVO);
                map.put("token", token);
                return Result.success("登录成功", map);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("系统内部错误");
        }
        return Result.fail("登录失败");
    }

    /**
     * 手机号登录：判断密码是否输入正确
     * @param phone 手机号
     * @param password 密码
     * @return
     */
    @RequestMapping("/phonelogin")
    public Result login(@RequestParam("phone") String phone, @RequestParam("password") String password){
        try {
            SysUser user = userService.getUser(phone);
            if (user == null) {
                return Result.fail("用户不存在");
            }
            //创建map，将jsonObject和user用户信息放入其中
            Map<String, Object> map = new HashMap<>();

            //对密码进行解密
            String decrypt = DESUtils.decrypt(user.getPassword());
            //判断输入的密码和真实密码是否一致
            if (decrypt.equals(password)) {
                //获取token
                String token = JwtUtils.setToken(SysUser.builder().phone(phone).build());
                map.put("user", user);
                map.put("token", token);
                return Result.success("登录成功", map);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("系统内部错误");
        }
        return Result.fail("密码不正确");
    }

    /***
     * 注册：判断手机号是否被注册，未被注册，则需要对密码passowrd进行加密处理，并将user信息插入用户表
     * @author renliqing
     * @since 2023-03-01
     * @param user 对象
     * @return result
     */
    @RequestMapping("/register")
    public Result register(@RequestBody SysUser user){
        SysUser sysUser = userService.getUser(user.getPhone());
        if(sysUser != null){
            return Result.fail("手机号已被注册");
        }
        //对密码进行加密
        String encrypt = DESUtils.encrypt(user.getPassword());
        user.setPassword(encrypt);
        //插入user
        boolean flag = userService.save(user);
        if(flag){
            return Result.success("注册成功",user);
        }
        return Result.success("注册失败");
    }


    /***
     * 修改个人信息
     * @author renliqing
     * @since 2023-03-01
     * @param user 对象
     * @param user 对象
     * @return result
     */
    @RequestMapping("/update")
    @Transactional
    public Result update(@RequestBody SysUser user){
        try {
            user.setModifiedTime(new Date());
            int count = userService.update(user);
            if(count>0) {
                //如果用户被修改，则修改动态表和评论表中的用户信息，判断是否修改过用户名
                if (user.getUsername() != null && !user.getUsername().equals("")) {
                    //判断是否存在被修改用户发布的动态，若存在，则需要修改动态表中的用户昵称
                    List<TrendVO> list1 = trendService.listTrends(user.getUserId());
                    if(list1.size()>0) {
                        //动态对象
                        SysTrend trend = SysTrend.builder()
                                .userId(user.getUserId())
                                .username(user.getUsername())
                                .modifiedTime(new Date())
                                .build();
                        //修改动态中的用户信息
                        trendService.update(trend);
                    }

                    //判断是否存在被修改用户的评论，若存在，则需要修改评论表中的用户昵称和头像
                    List<SysComment> list2 = commentService.listByUserId(user.getUserId());
                    if(list2.size()>0) {
                        //评论对象
                        SysComment comment = SysComment.builder()
                                .userId(user.getUserId())
                                .username(user.getUsername())
                                .build();
                        if (user.getUserPhoto() != null && !user.getUserPhoto().equals("")) {
                            comment.setUserPhoto(user.getUserPhoto());
                        }

                        //修改评论中的用户信息
                        commentService.update(comment);
                    }
                }

                //判断是否修改用户头像
                if(user.getUserPhoto() != null && !user.getUserPhoto().equals("")){
                    //判断是否存在被修改用户的评论，若存在，则需要修改评论表中的用户昵称和头像
                    List<SysComment> list2 = commentService.listByUserId(user.getUserId());
                    if(list2.size()>0) {
                        //评论对象
                        SysComment comment = SysComment.builder()
                                .userId(user.getUserId())
                                .userPhoto(user.getUserPhoto())
                                .build();
                        if (user.getUsername() != null && !user.getUsername().equals("")) {
                            comment.setUsername(user.getUsername());
                        }
                        //修改评论中的用户信息
                        commentService.update(comment);
                    }
                }
                return Result.success("更新成功", user);
            }
        }catch (Exception e){
            e.printStackTrace();
            //保证异常捕获之后事务还会回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("系统内部错误");
        }
        return Result.fail("用户信息更新失败");

    }


    /**
     * 用户关注的用户信息
     * @param userId
     * @return
     */
    @RequestMapping("/listfollow")
    public Result listfollow(@RequestParam("userId") Integer userId){
        //获取用户关注信息
        List<SysFollow> follows = followService.listFollow(userId);
        List<Integer> ids = new ArrayList<>();
        for(SysFollow fow:follows){
            ids.add(fow.getConcernedId());
        }
        //获取所有用户
        List<SysUser> list = userService.list(null);
        //过滤：获取到用户关注的用户信息
        List<SysUser> users = list.stream().filter(sysUser ->
                ids.contains(sysUser.getUserId())).collect(Collectors.toList());

        if(users.size()>0) {
            for (SysUser user : users) {
                if(user.getUserCity() != null && !user.getUserCity().equals("")) {
                    //将代表省市的数字转成具体的某个省份
                    String[] citys = user.getUserCity().split("-");
                    Integer index = Integer.parseInt(citys[0]);//省份下标
                    List<SysRegion> regions = regionService.listProvince();//省份集合
                    user.setUserCity(regions.get(index).getName());
                }
            }
        }
        return Result.success(users);

    }


    /**
     * 根据手机号修改密码
     * @param phone
     * @param newPass
     * @return
     */
    @RequestMapping("/changePass")
    public Result changePass(@RequestParam("phone") String phone,
                             @RequestParam("newPass") String newPass){
        SysUser user = userService.getUser(phone);
        if(user == null){
            return Result.fail("用户不存在");
        }
        //对密码进行加密
        String password = DESUtils.encrypt(newPass);
        int count = userService.updatePass(password, phone);
        if(count>0){
            return Result.success("密码修改成功");
        }
        return Result.fail("密码修改失败");

    }

}




























