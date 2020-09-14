package com.miku.service;

import com.miku.entity.TbUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.miku.utils.JsonResult;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author utopiamiku
 * @since 2020-09-13
 */
public interface TbUserService extends IService<TbUser> {

    public ArrayList<TbUser> queryAllUser();

    public JsonResult getAllUser(String key);

    public boolean addUser(TbUser user);

    public boolean alterUser(TbUser user);

    public boolean delUser(Integer id);
}
