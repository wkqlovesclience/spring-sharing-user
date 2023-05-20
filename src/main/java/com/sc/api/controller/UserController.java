package com.sc.api.controller;

import com.sc.api.dao.entity.User;
import com.sc.api.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wkqsclience118706@163.com
 * @date 2023/5/20 12:12 下午
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 根据手机号查询用户
     *
     * @param
     * @return User
     */
    @GetMapping("/queryUserByPhone")
    @ResponseBody
    public List<User> queryUserByPhone(Long phone) {
        return userService.queryUserByPhone(phone);
    }

    /**
     * 姓名查询用户
     *
     * @param
     * @return User
     */
    @GetMapping("/queryUserByName")
    @ResponseBody
    public List<User> queryUserByName(String name) {
        return userService.queryByName(name);
    }

    /**
     * 根据手机号查询用户
     *
     * @param
     * @return User
     */
    @GetMapping("/addRecord")
    @ResponseBody
    public Boolean addRecord(Long phone, String userName) {
        userService.saveRecord(phone, userName);
        return true;
    }
}
