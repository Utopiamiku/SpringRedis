package com.miku.utils;

import lombok.Data;

/**
 * @author: Utopiamiku
 * @date: 2020/9/13 8:47
 * @description:
 */
@Data
public class JsonResult<T> {

    private Integer codeState;
    private T obj;
    private String msg;

    public JsonResult(T obj){
        this.obj = obj;
    }
    public JsonResult(){}
}
