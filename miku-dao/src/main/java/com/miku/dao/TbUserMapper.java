package com.miku.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miku.entity.TbUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author utopiamiku
 * @since 2020-09-13
 */
@Mapper
public interface TbUserMapper extends BaseMapper<TbUser> {

    public ArrayList<TbUser> queryAllUser();


}
