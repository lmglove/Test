package com.example.lmgdd.controller;

import com.example.commons.util.DateUtils;
import com.example.lmgdd.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName : HelloSpringbootController
 * @Description : aa
 * @Author : LIAOMINGGANG
 * @Date: 2020-06-09 10:23
 */
@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloSpringbootController {

    @RequestMapping("/springboot")
    public User hello(@RequestBody User user){
        log.info("------------------谢小婷开始进入----------------------");
        String time = DateUtils.formatDate(new Date(), "yyyy-MM-dd hh:mm:ss");
        log.info("------------------谢小婷开始出去----------------------");
        return new User(user.getName(),time);
    }

}
