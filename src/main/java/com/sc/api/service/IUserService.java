package com.sc.api.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sc.api.dao.entity.User;

import java.util.List;

/**
 * @author wkqsclience118706@163.com
 * @date 2023/5/20 12:07 下午
 */
@DS("sharding")
public interface IUserService extends IService<User> {

    /**
     * 根据手机号查询用户
     *
     * @param
     * @return
     * @author wkqsclience118706@163.com
     * @date 2023/5/20 12:09 下午
     */
    List<User> queryUserByPhone(Long phone);

    /**
     * 保存用户
     *
     * @param phone
     * @param userName
     * @return void
     * @date 2023/5/20 12:09 下午
     */
    void saveRecord(Long phone, String userName);

    List<User> queryByName(String name);
}
