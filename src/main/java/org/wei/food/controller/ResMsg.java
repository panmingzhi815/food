package org.wei.food.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResMsg {
    private int code;
    private String msg;
    private Object data;

    public static ResMsg success(int code,Object data){
        return new ResMsg(code,null,data);
    }

    public static ResMsg fail(int code,String msg){
        return new ResMsg(code, msg, null);
    }
}
