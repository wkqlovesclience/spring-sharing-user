package com.sc.api.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户表
 *
 * @author wkqsclience118706@163.com
 * @date 2023/5/20 12:03 下午
 */
@TableName("user")
@Data
public class User {

    @TableId(value = "id", type = IdType.INPUT)
    private Long userId;

    @TableField("name")
    private String userName;

    private Long phone;
}
