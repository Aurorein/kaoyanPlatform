package com.kaoyan.commonUtils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Res {
    private Boolean success;

    private Integer code;

    private String message;

    private Map<String,Object> data = new HashMap<>();


    private Res(){}

    public static Res ok(){
        Res res = new Res();
        res.setSuccess(true);
        res.setCode(ResultCode.SUCCESS);
        res.setMessage("success");
        return res;
    }

    public static Res error(){
        Res res = new Res();
        res.setSuccess(false);
        res.setCode(ResultCode.ERROR);
        res.setMessage("failure");
        return res;
    }
    public Res success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Res message(String message){
        this.setMessage(message);
        return this;
    }

    public Res code(Integer code){
        this.setCode(code);
        return this;
    }

    public Res data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Res data(Map<String, Object> map){
        this.setData(map);
        return this;
    }


}
