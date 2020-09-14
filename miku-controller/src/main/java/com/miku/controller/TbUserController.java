package com.miku.controller;


import com.miku.entity.TbUser;
import com.miku.service.IJedisClient;
import com.miku.service.TbUserService;
import com.miku.utils.JsonResult;
import com.miku.utils.LoadRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author utopiamiku
 * @since 2020-09-13
 */
@RestController
@RequestMapping("/tbUser")
public class TbUserController {

    @Autowired
    private TbUserService service;
    @Autowired
    private IJedisClient client;

    @GetMapping("queryAll")
    public List<TbUser> queryAll() {
//    System.out.println("11111111111111");
//    List<TbUser> list = service.list();
//    System.out.println(list.size());
        return service.list();
    }

    @PostMapping("redisDemo")
    public String redisDemo(String key/*,String value*/) {
        return client.get(key/*, value*/);
    }

    //获取所有用户
    @GetMapping("getAllUser")
    public JsonResult getAllUser() {
        return service.getAllUser(LoadRedis.USER.getClazz());
    }

    //添加用户
    @PostMapping("addUser")
    public boolean addUser(TbUser user){

        return service.addUser(user);
    }
    @PostMapping("updateUser")
    public boolean updateUser(TbUser user){

        return service.alterUser(user);
    }

    @PostMapping("delUser")
    public boolean delUser(Integer id){

        return service.delUser(id);
    }

}

