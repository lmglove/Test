package com.example.lmgdd.entity;

import lombok.Data;
import sun.plugin2.message.Serializer;

import java.io.Serializable;

/**
 * @ClassName : User
 * @Description : 用户
 * @Author : LIAOMINGGANG
 * @Date: 2020-06-09 11:14
 */
@Data
public class User implements Serializable {

    //TODO AAA
    private String name;

    private String time;

    public User(String name) {
        this.name = name;
    }

    public User(String name, String time) {
        this.name = name;
        this.time = time;
    }
}
