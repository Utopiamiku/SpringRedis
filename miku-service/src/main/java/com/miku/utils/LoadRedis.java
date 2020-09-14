package com.miku.utils;

/**
 * @author: Utopiamiku
 * @date: 2020/9/13 16:11
 * @description:
 */
public enum LoadRedis {
    USER("com.miku.entity.TbUser");

    private String clazz;

    public String getClazz(){
        return clazz;
    }

    LoadRedis(String clazz){
        this.clazz = clazz;
    }
}
