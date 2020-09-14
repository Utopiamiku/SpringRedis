package com.miku.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.miku.dao.TbUserMapper;
import com.miku.entity.TbUser;
import com.miku.service.IJedisClient;
import com.miku.service.TbUserService;
import com.miku.utils.JsonResult;
import com.miku.utils.LoadRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author utopiamiku
 * @since 2020-09-13
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

    Gson gson = new Gson();
    @Autowired
    private IJedisClient jedis;

    @Autowired
    private TbUserMapper userMapper;

    @Override
    public ArrayList<TbUser> queryAllUser() {
        return userMapper.queryAllUser();
    }

    @Override
    public JsonResult getAllUser(String key) {
//        userData.load();
        ArrayList<TbUser> list = new ArrayList<>();

        Collection<String> values = jedis.hgetAll(key).values();
        Iterator<String> iterator = values.iterator();

        while (iterator.hasNext()) {
//            JSONObject jsonObject = JSON.parseObject(iterator.next());
//            Bank bank = JSONObject.toJavaObject(jsonObject, Bank.class);
//            b.add(bank);
            Gson gson = new Gson();
            //gson转对象
            TbUser tbUser = gson.fromJson(iterator.next(), TbUser.class);
            list.add(tbUser);
        }

        return new JsonResult(list);
    }

    @Override
    public boolean addUser(TbUser user) {
        boolean save = save(user);

//        userData.load();
        if (save && user.getId() != null) {
            jedis.hset(LoadRedis.USER.getClazz(), user.getId().toString(), gson.toJson(user));
            System.out.println("---缓存成功");
            return true;
        }
        return false;
    }
//乐观锁
    @Override
    public boolean alterUser(TbUser user) {
        //查询
        TbUser tbUser = getById(user.getId());
        user.setVersionId(tbUser.getVersionId());

        UpdateWrapper<TbUser> wrapper = new UpdateWrapper<>();
        wrapper.eq("version_id", user.getVersionId())
                .eq("id", user.getId());
        boolean b = update(user,wrapper);


//        boolean b = updateById(user);
        TbUser newUser = getById(user.getId());
        if (b) {
            jedis.hset(LoadRedis.USER.getClazz(), user.getId().toString(), gson.toJson(newUser));
            System.out.println("---缓存成功");
            return true;
        }
        return false;
    }

    @Override
    public boolean delUser(Integer id) {
        boolean b = removeById(id);
        if (b) {
            jedis.hdel(LoadRedis.USER.getClazz(), id.toString());
            System.out.println("---缓存成功");
            return true;
        }
        return false;
    }
}
