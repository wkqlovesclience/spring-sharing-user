package com.sc.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sc.api.dao.entity.User;
import com.sc.api.dao.mapper.UserMapper;
import com.sc.api.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * userService
 *
 * @author wkqsclience118706@163.com
 * @date 2023/5/20 12:07 下午
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * 根据手机号查询用户
     *
     * @param
     * @return User
     */
    @Override
    public List<User> queryUserByPhone(Long phone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getPhone, phone);
        return this.list(wrapper);
    }

    @Override
    public void saveRecord(Long phone, String userName) {
        User user = new User();
        user.setUserId(System.currentTimeMillis());
        user.setPhone(phone);
        user.setUserName(userName);
        this.save(user);
    }

    @Override
    public List<User> queryByName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getUserName, name);
        return this.list(wrapper);
    }
}
