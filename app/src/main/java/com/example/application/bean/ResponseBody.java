package com.example.application.bean;

import java.util.List;

/**
 * Created by dingmouren on 2018/7/23.
 */

public class ResponseBody<T> {
    private Integer code;
    private String msg;
    private List<T> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
