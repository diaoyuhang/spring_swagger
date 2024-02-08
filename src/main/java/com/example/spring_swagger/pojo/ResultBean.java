package com.example.spring_swagger.pojo;

public class ResultBean<T> {
    private String msg;
    private T date;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
