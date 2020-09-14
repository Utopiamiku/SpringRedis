package com.miku.config;

import com.google.gson.Gson;
import com.miku.entity.TbUser;
import com.miku.service.IJedisClient;
import com.miku.service.TbUserService;
import com.miku.utils.LoadRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author: Utopiamiku
 * @date: 2020/9/13 16:24
 * @description:
 */
@Configuration
@Slf4j
public class UserData {

    @Autowired
    private IJedisClient client;
    @Autowired
    private TbUserService userService;
    @PostConstruct
    public void load(){
        new Thread(()->{
            log.debug("---------------启动线程准备缓存数据----------------");
            log.debug("---------------1 删除 之前数据----------------");
            Set<String> keys = client.keys(LoadRedis.USER.getClazz() + "*");

            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                //删除子健
                long ms = client.del(key);
                log.debug(key+"---------------1 删除 ----------------"+ms);
            }
            log.debug("---------------删除完毕开始缓存----------------");
            List<TbUser> list = userService.list();
            for (TbUser b:list){
                Gson gson = new Gson();
                String s = gson.toJson(b);
                client.hset(LoadRedis.USER.getClazz(),b.getId().toString(),s);
            }
            log.debug("---------------缓存完毕----------------");

        }).start();
    }


}
